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
    
    fetch(API, {
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
            console.error(error);
            words = null;
        });
}

function getWord() {
    let ref = Math.floor(Math.random() * words.length);
    console.log(words)
    console.log(words[ref].japanese)
    currentWord = words[ref];
    return words[ref].japanese;
}

function getAnswer(text){
    if (currentWord.readings.indexOf(text) !== -1) {
        console.log("success")
        return true;
    }
    return false;
}

window.onload = function () {
    load();
}

module.exports = {getWord, getAnswer}
