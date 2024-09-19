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

function kanaConvertNew(text){
    // let test = text.replace(three, map3.get(text.charAt(0)).concat(map2.get(text.slice(1, 3))));
    let threes = text.match(three);
    const regex1 = /([kctshgdjbpnmr]|sh|ch)[y][aou]/
    // const str1 = 'table football, foosball';
    // let array1;

    // while ((array1 = regex1.exec(str1)) !== null) {
    //     console.log(`Found ${array1[0]}. Next starts at ${regex1.lastIndex}.`);
    // }
    let array = text.matchAll(three);
    for (let match of array) {
        
        console.log(match[0])
        let index = match.index;
        let found = match[0];
        let size = found.length;
        let half = Math.floor(size / 2);
        let partA = found.slice(0, half);
        let partB = found.slice(half, size);

        let replacementText = found.replace(partA, map3.get(partA)).replace(partB, map2.get(partB));
        return replacementText;
    }
    // let array2 = regex1.exec(test);
    // while ((array = regex1.exec(test)) !== null) {
    //     let index = array.index;
    //     let lastIndex = three.lastIndex;
    //     let difference = lastIndex - index;
    //     let offset = Math.floor(difference / 2);
    //     let partA = found.slice(0, difference - offset);
    //     let partB = found.slice(difference - offset, difference);
    //     let found = array[0];
    //     let replacementText = found.replace(partA, map3.get(partA)).replace(partB, map2.get(partB));
    //     return replacementText;
    // }

    // return text.replace(three, map3.get(text.charAt(0)).concat(map2.get(text.slice(1, 3))));
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