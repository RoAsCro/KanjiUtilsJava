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

test("Converts gdda to gっだ", () => {
    expect(kanaConvert("gdda")).toBe("gっだ");
})

test("Converts ggda to ggだ", () => {
    expect(kanaConvert("ggda")).toBe("ggだ");
})

test("Converts g to g", () => {
    expect(kanaConvert("g")).toBe("g");
})

test("Converts gg to gg", () => {
    expect(kanaConvert("gg")).toBe("gg");
})

test("Converts ggg to ggg", () => {
    expect(kanaConvert("ggg")).toBe("ggg");
})

test("Converts kag to かg", () => {
    expect(kanaConvert("kag")).toBe("かg");
})

test("Converts ig to いg", () => {
    expect(kanaConvert("ig")).toBe("いg");
})

test("Converts shyo to しょ", () => {
    expect(kanaConvert("shyo")).toBe("しょ");
})

test("Converts ggyk to ggyk", () => {
    expect(kanaConvert("ggyk")).toBe("ggyk");
})

test("Converts dgyo to dぎょ", () => {
    expect(kanaConvert("dgyo")).toBe("dぎょ");
})
test("Converts ggyo to っぎょ", () => {
    expect(kanaConvert("ggyo")).toBe("っぎょ");
})

test("Converts syo to しょ", () => {
    expect(kanaConvert("syo")).toBe("しょ");
})

test("Converts ggga to gっが", () => {
    expect(kanaConvert("ggga")).toBe("gっが");
})


test("Converts yyo to っよ", () => {
    expect(kanaConvert("yyo")).toBe("っよ");
})

test("Converts okyakusamairassyaimase to おきゃくさまいらっしゃいませ", () => {
    expect(kanaConvert("okyakusamairassyaimase")).toBe("おきゃくさまいらっしゃいませ");
})

test("Converts shi to し", () => {
    expect(kanaConvert("shi")).toBe("し");
})

test("Converts tau to たう", () => {
    expect(kanaConvert("tau")).toBe("たう");
    // PASING HERE, BUT NOT WHEN USED LIVE
})

test("Converts ndo to んど", () => {
    expect(kanaConvert("ndo")).toBe("んど");
})

test("Converts n to n", () => {
    expect(kanaConvert("n")).toBe("n");
})

test("Converts nj to nj", () => {
    expect(kanaConvert("nj")).toBe("nj");
})