const kanaConvert = require("../../../main/python/self/roashe/kanutils/frontend/javascript/kanaconvert.js")
const testFunction = require("./testFunction.js")


test("Testing jest", () => {
    expect(testFunction()).toBe(true);
})

test("Converts a to あ", () => {
    expect(kanaConvert("a")).toBe("あ");
})