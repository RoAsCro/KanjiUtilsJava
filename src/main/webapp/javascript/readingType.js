var questionCount = 0;
var answers = 0;

var words = null;
var currentWord;
var convertedReadings = [];

var failed = [];
var failedWord = false;

var repeat = true;

const API = "http://127.0.0.1:8080/api/vocab/vocab";
const KATAKANISE = "http://127.0.0.1:8080/api/kana/katakanise";


async function load() {
    console.log("Loading")
    if (words !== null) {
        usedWords = [];
        return;
    }
    
    await fetch(API, {
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

async function beginLoad(ms){
    if (words !== null) {
        document.getElementById("wordLanding").innerHTML = getWord();
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

function getWord() {
    console.log(repeat);
    console.log(words.length);

    if (failedWord) {
        failed.splice(failed.indexOf(currentWord), 1);
        failedWord = false;
    }

    let choice = Math.floor(Math.random() * 10);

    let usingFailed = choice > 7 && failed.length > 0;

    if (usingFailed) {
        failedWord = true;
    }
    let wordSet = usingFailed ? failed : words;

    let ref = Math.floor(Math.random() * wordSet.length);
    currentWord = wordSet[ref];

    let english = currentWord.english;
    convertedReadings = currentWord.readings.map(k => wanakana.toHiragana(k));
    console.log(convertedReadings);
    document.getElementById("englishLanding").innerHTML = (english.slice(0, 3) + "").replaceAll(",", ", ");
    document.getElementById("score").innerHTML = answers + "/" + questionCount;
    document.getElementById("readingLanding").innerHTML = (currentWord.readings + "").replaceAll(",", ", ");
    if (wanakana.isKana(currentWord.japanese)) {
        $("#wordLanding").hide();
    }
    return currentWord.japanese;
}

function getAnswer(text){
    if (words === null) {
        return false;
    }
    text = wanakana.toHiragana(text);
    if (convertedReadings.indexOf(text) !== -1) {
        questionCount += 1;
        answers += 1;
        if (!repeat) {
            words.splice(words.indexOf(currentWord), 1);
        }
        return true;
    }
    
    return false;
}

function pass() {
    questionCount += 1;
    failed.push(currentWord);
}


window.onload = function () {
    beginLoad(1000);
}

module.exports = {getWord, getAnswer}
