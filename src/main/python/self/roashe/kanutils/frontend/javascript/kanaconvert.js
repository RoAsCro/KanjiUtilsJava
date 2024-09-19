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

function kanaConvert(text) {
    let currentString = "";
    let returnString = "";
    for (let i = 0 ; i < text.length ; i++) {
        currentString = currentString.concat(text.charAt(i));
        let retrieved = map.get(currentString);
        let addition = "";

        if (currentString.length === 3) {
            let charOne = currentString.charAt(0);
            let charTwo = currentString.charAt(1);
            let charThree = currentString.charAt(2);
            let suffix = currentString.slice(1, 3);
            if (charOne === charTwo) {
                addition = "っ";
                retrieved = map.get(suffix);
                // if (retrieved !== undefined) {
                //     returnString = returnString.concat("っ", retrieved);
                //     currentString = "";
                // }
            } else {
                addition = charOne;
                retrieved = map.get(suffix);
            }
        }
        if (retrieved !== undefined) {
            returnString = returnString.concat(addition, retrieved);
            currentString = "";
        }  

    }
    returnString = returnString.concat(currentString);
    return returnString;
}



module.exports = kanaConvert;