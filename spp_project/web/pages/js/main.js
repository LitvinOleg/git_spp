/* Reload main page when button clicked */
;(function() {
    var bigButtons = document.getElementsByClassName("big-button");
    var i;
    var alertFunc = function () {
        alert("ghj");
    };
    
    for (i=0; i<bigButtons.length; i++)
        bigButtons[i].addEventListener("click", alertFunc, false);
}());