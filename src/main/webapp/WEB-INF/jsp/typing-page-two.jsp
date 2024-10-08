<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../../styles/styles.css" />
    <script type="text/javascript" src="../../javascript/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="../../javascript/wanakana.min.js"></script>
    <script type="text/javascript" src="../../javascript/readingType.js"></script>

    <title>${attributes.getTitle()} - RoAshe</title>
</head>
<body class="container">
    <p id="score">0/0</p>

    <div class="container" style="flex-direction: column; flex-wrap: nowrap;">
        <div class="container">
            <p class="prominentWord" id="wordLanding"
                <c:if test='${attributes.getHideWord()}'>style="display: none;"></c:if>>Loading...</p>
        </div>
        <p class="prominentWord" style="font-size: 5vh; <c:if test='${attributes.getHideEnglish()}'>display: none;</c:if>" id="englishLanding">Loading...</p>
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
        wanakana.bind($("#answerType")[0]);
        function answer() {
            let correct = getAnswer(this.value);
            if (correct) {
                answerGiven();
            }
        }
        function answerGiven() {
            japaneseLanding.show();
            <c:if test='${attributes.getHideEnglish()}'>
            // Hide word
            englishLanding.show();
            </c:if>

            // Swap out buttons
            nextButton.show();
            passButton.hide();

            // Swap out readings/textbox
            readingLanding.show();
            textInput.hide();

            // Rebind keys
            $(document).unbind("keypress.pass")
            $(document).bind("keypress.next", k => {if (k.which === 13){nextQuestion();}});
            $(document).bind("keypress.fail", k => {if (k.which === 107){pass();nextQuestion();}});
        }
        function callPass(){
            answerGiven();
            pass();
        }
        function nextQuestion() {
            <c:if test='${attributes.getHideWord()}'>
            // Hide word
            $("#wordLanding").hide();
            </c:if>
            <c:if test='${attributes.getHideEnglish()}'>
            // Hide English
            englishLanding.hide();
            </c:if>
            // Swap out buttons
            passButton.show();
            nextButton.hide();

            // Swap out readings/textbox
            readingLanding.hide();
            textInput.show();

            // Place word
            getWord();

            // Reset textarea
            textInput.val("");
            textInput.focus();

            // Rebind keys
            $(document).unbind("keypress.next")
            $(document).unbind("keypress.fail")
            $(document).bind("keypress.pass", k => {if (k.which === 13){callPass();}});
        }
        
        // Set variables
        const nextButton = $("#next");
        const passButton = $("#pass");
        const textInput = $("#answerType")

        reverseKana = <c:choose>
                <c:when test='${attributes.getReverseKana()}'>
                    true
                </c:when>
                <c:otherwise>
                    false
                </c:otherwise>
            </c:choose>
            ;
        repeat = <c:choose>
                <c:when test='${attributes.getRepeat()}'>
                    true
                </c:when>
                <c:otherwise>
                    false
                </c:otherwise>
            </c:choose>
            ;
        APISuffix = "${attributes.getEndpoint()}";
        <%-- <c:if test='${not empty tags}'> --%>
        params.set("tags", "${attributes.getTags()}");
        <%-- </c:if> --%>
        englishLanding = $("#englishLanding");
        japaneseLanding = $("#wordLanding");
        readingLanding =  $("#readingLanding");
        score = $("#score");

        // Set events
        textInput.on("input", answer);
        passButton.on("click", callPass);
        nextButton.on("click", nextQuestion);
        $(document).bind("keypress.pass", k => {if (k.which === 13){callPass();}});
    </script>
</body>
</html>