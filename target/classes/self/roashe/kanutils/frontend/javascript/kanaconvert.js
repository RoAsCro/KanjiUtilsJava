const map = new Map([
    ["a", "あ"], ["ka", "か"], ["sa", "さ"], ["ta", "た"], ["na", "な"], ["ha", "は"],
    ["ma", "ま"], ["ya", "や"], ["ra", "ら"], ["wa", "わ"], ["ga", "が"], ["za", "ざ"],
    ["da", "だ"], ["ba", "ば"], ["pa", "ぱ"],
    ["i", "い"], ["ki", "き"], ["si", "し"], ["shi", "し"], ["chi", "ち"], ["ni", "に"], ["hi", "ひ"],
    ["mi", "み"], ["ri", "り"], ["gi", "ぎ"], ["ji", "じ"], ["zi", "じ"],
    ["di", "ぢ"], ["bi", "び"], ["pi", "ぴ"],
    ["u", "う"], ["ku", "く"], ["su", "す"], ["tu", "つ"], ["tsu", "つ"], ["nu", "ぬ"], ["fu", "ふ"],
    ["mu", "む"], ["yu", "ゆ"], ["ru", "る"], ["gu", "ぐ"], ["zu", "ず"],
    ["du", "づ"], ["bu", "ぶ"], ["pu", "ぷ"],
    ["e", "え"], ["ke", "け"], ["se", "せ"], ["te", "て"], ["ne", "ね"], ["he", "へ"],
    ["me", "め"], ["re", "れ"], ["ge", "げ"], ["ze", "ぜ"],
    ["de", "で"], ["be", "べ"], ["pe", "ぺ"],
    ["o", "お"], ["ko", "こ"], ["so", "そ"], ["to", "と"], ["no", "の"], ["ho", "ほ"],
    ["mo", "も"], ["yo", "よ"], ["ro", "ろ"], ["wo", "を"], ["go", "ご"], ["zo", "ぞ"],
    ["do", "ど"], ["bo", "ぼ"], ["po", "ぽ"],
    ["vu", "ゔ"], ["nn", "ん"]
]);

const map2 = new Map([
    ["ya", "ゃ"], ["yo", "ょ"], ["yu", "ゅ"] 
]);

const map3 = new Map([
    ["k", "き"], ["c", "ち"], ["ch", "ち"], ["t", "ち"], ["s", "し"], ["sh", "し"], ["h", "ひ"], ["g", "ぎ"], ["d", "ぢ"],
    ["j", "じ"], ["b", "び"], ["p", "ぴ"], ["n", "に"], ["m", "み"], ["r", "り"]
]);



const latin = new RegExp("[a-z]", "u");
const y =  "[y][aou]";
const sy = "([kctshgdjbpnmr]|sh|ch)";
const three = /([kctshgdjbpnmr]|sh|ch)[y][aou]/g;
const twoPlus = /[b-df-hj-np-tv-z]h[aeiou]/g
const two = /[b-df-hj-np-tv-z][aeiou]/g
const vowel = /[aeiou]/g
const nConsonant = /n[b-df-hj-np-tv-xz]/g
const double = /([b-df-hj-np-tv-z])\1h?[aeiou]/g
const doubleThree = /([b-df-hj-np-tv-z])\1h?[y][aou]/g

function kanaConvertNew(text){
    text = text.replaceAll("nn", "ん");

    text = text.replaceAll(nConsonant, (ji) => "ん".concat(ji.charAt(1)));

    text = text.replaceAll(double, (ji) => map.has(ji.slice(1, ji.length)) ? "っ".concat(ji.slice(1, ji.length)) : ji);

    text = text.replaceAll(doubleThree, (ji) => {
        let string = ji.slice(1, ji.length);
        let char1 = string.slice(0, Math.floor(string.length / 2))
        let char2 = string.slice(string.length / 2, string.length)
        let test = (map3.has(char1) && map2.has(char2));
        return test ? "っ".concat(map3.get(char1).concat(map2.get(char2))) : ji;
    });

    text = text.replaceAll(three, (ji) => {
        let char1 = ji.slice(0, Math.floor(ji.length / 2))
        let char2 = ji.slice(ji.length / 2, ji.length)
        
        return (map3.has(char1) && map2.has(char2)) ? map3.get(char1).concat(map2.get(char2)) : ji;
    });

    text = text.replaceAll(twoPlus, (ji) => map.has(ji) ? map.get(ji) : ji);

    text = text.replaceAll(two, (ji) => map.has(ji) ? map.get(ji) : ji);
    
    text = text.replaceAll(vowel, (ji) => map.has(ji) ? map.get(ji) : ji);

    return text;
}

function kanaConvert(text) {
    let currentString = "";
    let returnString = "";
    for (let i = 0 ; i < text.length ; i++) {
        let current = text.charAt(i);
        currentString = currentString.concat(current);
        if (!latin.test(current)) {
            returnString = returnString.concat(currentString.replace("n", "ん"));
            currentString = "";
            continue;
        }
        let retrieved = map.get(currentString);
        let addition = "";
        let charOne = currentString.charAt(0);
        let toAdd = "";
        if (retrieved === undefined) {
            if (currentString.length === 4) {
                let prefix = currentString.slice(0, 2);
                let suffix = currentString.slice(2, 4);
                retrieved = map2.get(suffix);
                addition = map3.get(prefix);
                if (addition === undefined || retrieved === undefined) {
                    toAdd = charOne;
                    let charTwo = currentString.charAt(1);
                    if (charOne === charTwo) {
                        addition = "っ";
                    } else {
                        addition = charOne;
                    }
                    currentString = currentString.slice(1, 4);
                }
            }
            if (currentString.length === 3) {
                
                charOne = currentString.charAt(0);
                toAdd = toAdd.concat(charOne)
                let charTwo = currentString.charAt(1);
                let charThree = currentString.charAt(2);
                let suffix = currentString.slice(1, 3);
                if (charThree === "y") {
                    continue;
                
                } else if (charTwo === "y" && map3.has(charOne)) {
                    addition = addition.concat(map3.get(charOne));
                    retrieved = map2.get(suffix);
                } else if (charOne === charTwo) {
                    addition = "っ";
                    retrieved = map.get(suffix);
                } else {
                    addition = charOne;
                    retrieved = map.get(suffix);
                }
                currentString = suffix;
            }
        }
        if (retrieved !== undefined) {
            returnString = returnString.concat(addition, retrieved);
            currentString = "";
        }  else {
            returnString = returnString.concat(toAdd);
        }

    }
    returnString = returnString.concat(currentString);
    let finalChar =  returnString.charAt(returnString.length - 1);
    let offset = 1;
    if (finalChar == "y") {
        offset = 2;
    }
    return returnString.slice(0, returnString.length - offset).replace("n", "ん").concat(returnString.slice(returnString.length - offset));
}



module.exports = {kanaConvert, kanaConvertNew};