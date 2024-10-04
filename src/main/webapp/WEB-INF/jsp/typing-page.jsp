<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../../styles/styles.css" />
    <script type="text/javascript" src="../../javascript/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="../../javascript/wanakana.min.js"></script>
    <script type="text/javascript" src="../../javascript/readingType.js"></script>

    <title>${title} - RoAshe</title>
</head>
<body class="container">
    <p id="score">0/0</p>

    <div class="container" style="flex-direction: column; flex-wrap: nowrap;">
        <div class="container">
            <p class="prominentWord" id="wordLanding"
                <c:if test='${showWord}'>style="display: none;"></c:if>>Loading...</p>
        </div>
        <p class="prominentWord" style="font-size: 5vh;" id="englishLanding">Loading...</p>
        <p class="prominentWord" id="readingLanding" style="display: none;"></p>
            
        <div id="inputContainer" >
            <textarea class="prominentWord" id="answerType" name="answerType"></textarea>
        </div>
    </div>
    <div>
        <button id="pass">Pass</button>
        <button id="next" style="display: none;">Continue</button>
    </div>

    <script>
        wanakana.bind($(answerType)[0]);
        function answer() {
            let correct = getAnswer(this.value);
            if (correct) {
                answerGiven();
            }
        }
        function answerGiven() {
            <c:if test='${showWord}'>
                // Show word
                $("#wordLanding").show();
            </c:if>
            // Swap out buttons
            $("#next").show();
            $("#pass").hide();

            // Swap out readings/textbox
            $("#readingLanding").show();
            $("#answerType").hide();

            // Rebind keys
            $(document).unbind("keypress.pass")
            $(document).bind("keypress.next", k => {if (k.which === 13){nextQuestion();}});
        }
        function callPass(){
            answerGiven();
            pass();
        }
        function nextQuestion() {
            <c:if test='${showWord}'>
                // Hide word
                $("#wordLanding").hide();
            </c:if>
            // Swap out buttons
            $("#pass").show();
            $("#next").hide();

            // Swap out readings/textbox
            $("#readingLanding").hide();
            $("#answerType").show();

            // Place word
            $("#wordLanding").html(getWord());

            // Reset textarea
            $("#answerType").val("");
            $("#answerType").focus();

            // Rebind keys
            $(document).unbind("keypress.next")
            $(document).bind("keypress.pass", k => {if (k.which === 13){callPass();}});
        }
        
        $("#answerType").on("input", answer);
        $("#pass").on("click", callPass);
        $("#next").on("click", nextQuestion);
        $(document).bind("keypress.pass", k => {if (k.which === 13){callPass();}});
    </script>
</body>
</html>