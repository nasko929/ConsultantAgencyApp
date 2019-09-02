try {
    var currPassField = document.getElementById("password");
    currPassField.addEventListener("load", matchingPasswords());
} catch (e) {

}

function matchingPasswords() {
    var currPassField = document.getElementById("password");
    var currConfPassField = document.getElementById("confPassword");
    if (currPassField != null && currConfPassField != null) {
        setInterval(function () {
            if (currPassField.value == currConfPassField.value) {
                document.getElementById("matchingPasswords").style.color = "green";
                document.getElementById("matchingPasswords").innerHTML = "Passwords match.";
            } else {
                document.getElementById("matchingPasswords").style.color = "red";
                document.getElementById("matchingPasswords").innerHTML = "Passwords don't match.";
            }
        }, 500);
    }
}

function alterReportCommentSending(typeOfAction, prefix) {
    var div = document.getElementById("formAction");
    var legend = document.getElementById("legendTitle");
    var submitButton = document.getElementById("submitButton");
    var comment = document.getElementById("comment");
    if (typeOfAction == "Accept") {
        document.commentForm.action = prefix + "/customer/logged/accept_report";
        div.style.display = "block";
        comment.value = "Write your last words about the work with this consultant...";
        legend.innerHTML = "Accepting the Report";
        submitButton.value = "Accept the report";
    } else {
        document.commentForm.action = prefix + "/customer/logged/reject_report";
        div.style.display = "block";
        comment.value = "Write the reasons of rejection...";
        legend.innerHTML = "Rejecting the Report";
        submitButton.value = "Reject the report";
    }
}
