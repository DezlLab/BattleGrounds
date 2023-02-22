//
////By Luca P. and Theo L. no License///
//
//Version
let appVersion = "1.0"

function setup(){
    document.getElementById("header").innerHTML += appVersion
    onClr()

    var seperators = document.getElementsByClassName("splitter")
    for(var i = 0; i < seperators.length; i++){
        console.log()
        if(getComputedStyle(seperators[i]).getPropertyValue("cursor")=="col-resize"){
            dragElement( seperators[i], "H", i)
        }
        else{
            dragElement( seperators[i], "V", i)
        }
    }

}

//OnClicks
function onRun(){
    addText('outputArea', "\nOutput: ", fetchElementValue("java.run", 'codeArea'))
    scrollDown('outputArea')
}

function onClr(){
    console.log("sd")
    document.getElementById('outputArea').value = "GroundZ by Luca P. and Theo L. " + appVersion
}

function scrollDown(elementId){
    let element = document.getElementById(elementId)
    element.scroll({
        top: element.scrollHeight,
        behavior: 'smooth'
      })
}

//Http request to server with url and body and a receiver for the result
async function fetchElementValue(url, elementId){
    return fetch(url, {
        method : 'POST',
        body : getText(elementId),
    }).then(
        (response) => response.text()
    )
}

//Get value from element if there!!!
function getText(elementId){
    return document.getElementById(elementId).value
}

//Add text to Element
function addText(elementId, before, text){
    text.then(
        text => document.getElementById(elementId).value += before + text
    ).catch(
        e => document.getElementById(elementId).value += "\nERROR : " + e
    )
    
}




function dragElement(splitter, direction, id){
    splitter.onmousedown = onMouseDown;
    
    var initialPos
    
    function onMouseDown(e)
    {
        //console.log("mouse down: " + e.clientY);
        initialPos = e.clientX
        document.documentElement.style.cursor = "col-resize";
        if(direction == "V")
        {
            document.documentElement.style.cursor = "row-resize";
        }
        document.onmousemove = onMouseMove;
        document.onmouseup = () => {
            //console.log("mouse up");
            document.documentElement.style.cursor = "default";
            document.onmousemove = document.onmouseup = null;
        }
    }

    function onMouseMove(e)
    {
        var max = splitter.parentElement.clientWidth
        var curPos = e.clientX
        var posOff = splitter.parentElement.getBoundingClientRect().x
        if(direction == "V")
        {
            //console.log(splitter.parentElement.getBoundingClientRect().y)
            max = splitter.parentElement.clientHeight
            posOff = splitter.parentElement.getBoundingClientRect().y
        }
        if(direction == "V")
        {
            curPos = e.clientY
        }
        var val = max - (max - curPos + posOff)
        var handelSnap = 50//getStorage(1)
        var handel = 10 //TODO redo storage
        if(val < handelSnap || curPos < 0){val = 0}
        if(val > max - handelSnap - handel){val = max - handel}
            document.documentElement.style.setProperty("--Splt"+id, val+"px");
        //console.log("mouse move: " + e.clientX);
    }
}