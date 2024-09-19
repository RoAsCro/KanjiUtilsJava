const kanaConvert = require("../../../main/python/self/roashe/kanutils/frontend/javascript/kanaconvert.js")
const testFunction = require("./testFunction.js")


test("Testing jest", () => {
    expect(testFunction()).toBe(true);
})

test("Converts a to あ", () => {
    expect(kanaConvert("a")).toBe("あ");
})

test("Converts ka to か", () => {
    expect(kanaConvert("ka")).toBe("か");
})


test("Converts nn to ん", () => {
    expect(kanaConvert("ka")).toBe("か");
})

test("Converts dda to っだ", () => {
    expect(kanaConvert("dda")).toBe("っだ");
})

test("Converts gda to gだ", () => {
    expect(kanaConvert("gda")).toBe("gだ");
})