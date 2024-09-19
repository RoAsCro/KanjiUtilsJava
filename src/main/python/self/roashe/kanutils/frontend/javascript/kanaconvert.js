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

const map２ = new Map([
    ["ya", "ゃ"], ["yo", "ょ"], ["yu", "ゅ"] 
]);

function kanaConvert(text) {
    let currentString = "";
    let returnString = "";
    for (let i = 0 ; i < text.length ; i++) {
        currentString = currentString.concat(text.charAt(i));
        let retrieved = map.get(currentString);
        if (retrieved !== undefined) {
            returnString = returnString.concat(retrieved);
            currentString = "";
        }

    }
    return returnString;
}



module.exports = kanaConvert;