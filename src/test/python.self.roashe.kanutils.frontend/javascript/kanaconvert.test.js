const kanaConvertModule = require("../../../main/python/self/roashe/kanutils/frontend/javascript/kanaconvert.js");
const kanaConvert = kanaConvertModule.kanaConvert;
const kanaConvertNew = kanaConvertModule.kanaConvertNew;
const testFunction = require("./testFunction.js");


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

test("Converts たu to たう", () => {
    expect(kanaConvert("たu")).toBe("たう");
})

test("Converts ndo to んど", () => {
    expect(kanaConvert("ndo")).toBe("んど");
})

test("Converts n to n", () => {
    expect(kanaConvert("n")).toBe("n");
})

test("Converts nj to んj", () => {
    expect(kanaConvert("nj")).toBe("んj");
})

test("Converts njb to んjb", () => {
    expect(kanaConvert("njb")).toBe("んjb");
})

test("Converts nsyo to んしょ", () => {
    expect(kanaConvert("nsyo")).toBe("んしょ");
})

test("Converts nし to んし", () => {
    expect(kanaConvert("nし")).toBe("んし");
})

test("Converts ny to ny", () => {
    expect(kanaConvert("ny")).toBe("ny");
})




test("Converts syo to しょ", () => {
    expect(kanaConvertNew("syo")).toBe("しょ");
})

test("Converts kyo to きょ", () => {
    expect(kanaConvertNew("kyo")).toBe("きょ");
})

test("Converts hyo to ひょ", () => {
    expect(kanaConvertNew("hyo")).toBe("ひょ");
})

test("Converts nyo to にょ", () => {
    expect(kanaConvertNew("nyo")).toBe("にょ");
})

test("Converts shyo to しょ", () => {
    expect(kanaConvertNew("shyo")).toBe("しょ");
})

test("Converts syosyo to しょしょ", () => {
    expect(kanaConvertNew("syosyo")).toBe("しょしょ");
})

test("Converts fsyofsyof to fしょfしょf", () => {
    expect(kanaConvertNew("fsyofsyof")).toBe("fしょfしょf");
})

test("Converts fsyofchyuf to fしょfちゅf", () => {
    expect(kanaConvertNew("fsyofchyuf")).toBe("fしょfちゅf");
})

test("Converts a to あ", () => {
    expect(kanaConvertNew("a")).toBe("あ");
})

test("Converts u to う", () => {
    expect(kanaConvertNew("u")).toBe("う");
})

test("Converts au to あう", () => {
    expect(kanaConvertNew("au")).toBe("あう");
})

test("Converts aa to ああ", () => {
    expect(kanaConvertNew("aa")).toBe("ああ");
})

test("Converts ka to か", () => {
    expect(kanaConvertNew("ka")).toBe("か");
})


test("Converts nn to ん", () => {
    expect(kanaConvertNew("ka")).toBe("か");
})

test    ("Converts dda to っだ", () => {
    expect(kanaConvertNew("dda")).toBe("っだ");
})

test("Converts gda to gだ", () => {
    expect(kanaConvertNew("gda")).toBe("gだ");
})

test("Converts gdda to gっだ", () => {
    expect(kanaConvertNew("gdda")).toBe("gっだ");
})

test("Converts ggda to ggだ", () => {
    expect(kanaConvertNew("ggda")).toBe("ggだ");
})

test("Converts g to g", () => {
    expect(kanaConvertNew("g")).toBe("g");
})

test("Converts gg to gg", () => {
    expect(kanaConvertNew("gg")).toBe("gg");
})

test("Converts ggg to ggg", () => {
    expect(kanaConvertNew("ggg")).toBe("ggg");
})

test("Converts kag to かg", () => {
    expect(kanaConvertNew("kag")).toBe("かg");
})

test("Converts ig to いg", () => {
    expect(kanaConvertNew("ig")).toBe("いg");
})

test("Converts shyo to しょ", () => {
    expect(kanaConvertNew("shyo")).toBe("しょ");
})

test("Converts ggyk to ggyk", () => {
    expect(kanaConvertNew("ggyk")).toBe("ggyk");
})

test("Converts dgyo to dぎょ", () => {
    expect(kanaConvertNew("dgyo")).toBe("dぎょ");
})
test("Converts ggyo to っぎょ", () => {
    expect(kanaConvertNew("ggyo")).toBe("っぎょ");
})

test("Converts syo to しょ", () => {
    expect(kanaConvertNew("syo")).toBe("しょ");
})

test("Converts ggga to gっが", () => {
    expect(kanaConvertNew("ggga")).toBe("gっが");
})


test("Converts yyo to っよ", () => {
    expect(kanaConvertNew("yyo")).toBe("っよ");
})

test("Converts okyakusamairassyaimase to おきゃくさまいらっしゃいませ", () => {
    expect(kanaConvertNew("okyakusamairassyaimase")).toBe("おきゃくさまいらっしゃいませ");
})

test("Converts shi to し", () => {
    expect(kanaConvertNew("shi")).toBe("し");
})

test("Converts たu to たう", () => {
    expect(kanaConvertNew("たu")).toBe("たう");
})

test("Converts ndo to んど", () => {
    expect(kanaConvertNew("ndo")).toBe("んど");
})

test("Converts n to n", () => {
    expect(kanaConvertNew("n")).toBe("n");
})

test("Converts nj to んj", () => {
    expect(kanaConvertNew("nj")).toBe("んj");
})

test("Converts njb to んjb", () => {
    expect(kanaConvertNew("njb")).toBe("んjb");
})

test("Converts nsyo to んしょ", () => {
    expect(kanaConvertNew("nsyo")).toBe("んしょ");
})

test("Converts ny to ny", () => {
    expect(kanaConvertNew("ny")).toBe("ny");
})

test("Converts bhi to bひ", () => {
    expect(kanaConvertNew("bhi")).toBe("bひ");
})
