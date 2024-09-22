// var words = null;
// var usedWords = [];
// const API = "https://127.0.0.1:8080/api/vocab/vocab";

// async function load() {
//     console.log("Loaded")
//     if (words !== null) {
//         usedWords = [];
//         return;
//     }
//     fetch(API, {
//         mode: 'cors',
//         headers: {
//           'Access-Control-Allow-Origin':'*'
//         }})
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error("Ro, you didn't start the server");
//             }
//             words = response.json();
//         })
//         .catch(error => {
//             console.error(error);
//             words = null;
//         });
// }

var words = null;
var usedWords = [];
var currentWord;

const API = "http://127.0.0.1:8080/api/vocab/vocab";


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
    console.log("Outer")
    if (ms > 5000) {
        console.log("returnign");
        return;
    }
    await load();
    sleep(ms).then(beginLoad(ms + 1000));
    
}

function sleep(ms) {
    return new Promise(() => setTimeout(() => {}, ms));
}

function getWord() {
    let ref = Math.floor(Math.random() * words.length);
    currentWord = words[ref];
    return words[ref].japanese;
}

function getAnswer(text){
    console.log("Trying")
    if (words === null) {
        return false;
    }
    if (currentWord.readings.indexOf(text) !== -1) {
        console.log("success")
        return true;
    }
    
    return false;
}



window.onload = function () {
    beginLoad(1000);
}

module.exports = {getWord, getAnswer}
