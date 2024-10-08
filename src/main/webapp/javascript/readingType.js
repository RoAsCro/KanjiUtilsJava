var questionCount = 0;
var answers = 0;

var words = null;
var currentWord;
var convertedReadings = [];

var failed = [];
var failedWord = false;

var repeat = true;
var reverseKana = true;
var params = new URLSearchParams();

// JQuery DOM objects
var englishLanding, japaneseLanding, readingLanding, score;

const API = "http://127.0.0.1:8080/api/";
var APISuffix = "vocab/vocab";
const APIQ = "?";

// Load words from backend
async function load() {
    console.log("Loading")
    if (words !== null) {
        usedWords = [];
        return;
    }
    
    await fetch(API+APISuffix+APIQ+params.toString(), {
        mode: 'cors',
        headers: {
        'Access-Control-Allow-Origin':'*'
        }})
        .then(response => {
            if (!response.ok) {
                throw new Error("Ro, you didn't start the server");
            }
            console.log("Loaded")
            return response.json()

        })
        .then(data => {
            words = data
        })
        .catch(error => {
            console.log("Failed to load")
        })
}
// Begin the loading process, allowing for access while server is starting up
async function beginLoad(ms){
    if (words !== null) {
        getWord();
        return;
    }
    if (ms > 5000) {
        return;
    }
    await load();
    sleep(ms).then(beginLoad(ms + 1000));
    
}

function sleep(ms) {
    return new Promise(() => setTimeout(() => {}, ms));
}

// Get a new word and set values in the document
function getWord() {
    if (!repeat) {
        words.splice(words.indexOf(currentWord), 1);
    }
    console.log(params.get("tags"));
    console.log(repeat);
    console.log(words.length + ";" + failed.length);

    if (failedWord) {
        failed.splice(failed.indexOf(currentWord), 1);
    }

    
    let choice = Math.floor(Math.random() * 10);

    let usingFailed = words.length == 0 || (choice > 8 && failed.length > 0);

    failedWord = usingFailed
    let wordSet = usingFailed ? failed : words;

    let ref = Math.floor(Math.random() * wordSet.length);
    currentWord = wordSet[ref];

    let english = currentWord.english;
    convertedReadings = currentWord.readings.map(k => wanakana.toHiragana(k)).map(k => k.replaceAll("。", ""));
    let readings = currentWord.readings.map(k => k.replaceAll("。", "")).filter(k => wanakana.isKana(k));

    console.log(convertedReadings);
    englishLanding.html((english + "").replaceAll(",", ", "));
    japaneseLanding.html(currentWord.japanese);
    readingLanding.html((readings + "").replaceAll(",", ", "));
    score.html(answers + "/" + questionCount);
    if (reverseKana && wanakana.isKana(currentWord.japanese)) {
        englishLanding.show();
        japaneseLanding.hide();
    }
}

function getAnswer(text){
    if (words === null) {
        return false;
    }
    text = wanakana.toHiragana(text);
    if (convertedReadings.indexOf(text) !== -1) {
        // TODO - succeed on pressing enter instead
        questionCount += 1;
        answers += 1;
        return true;
    }
    
    return false;
}

function pass() {
    if (failed.indexOf(currentWord) == -1) {
        questionCount += 1;
        failed.push(currentWord);
    }
}


window.onload = function () {
    beginLoad(1000);
}
