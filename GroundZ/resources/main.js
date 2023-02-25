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
    fetchElementValue("java.run", 'codeArea').then(
        response => processData(JSON.parse(response))
    ).catch(
        //e => document.getElementById(elementId).value += "\nERROR : " + e
    )
    
    scrollDown('outputArea')
}

function onClr(){
    console.log("sd")
    document.getElementById('outputArea').value = "GroundZ by Luca P. and Theo L. " + appVersion


    backBlocks = document.getElementsByClassName("backBlock")
    for (i = 1; i < backBlocks.length; i++){
        backBlocks[i].innerHTML = ""
    }
}

function onContinue(){
    console.log("SUSUUUSUSUss")
    fetchElementValue("java.continue", 'codeArea').then(
        response => processData(JSON.parse(response))
    ).catch(
        e => document.getElementById(elementId).value += "\nERROR : " + e
    )
    
    scrollDown('outputArea')
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

async function processData(jsonData){
    if(jsonData != null){
        addText('outputArea', "\nPROG : ", jsonData["textData"])
        if(!jsonData["endOfData"]){
            //onContinue()
        }

        playerMoves = jsonData["playerMoves"]
        console.log(playerMoves)
        first = true
        gX = 0
        gY = 0
        xSize = 4
        backBlocks = document.getElementsByClassName("backBlock")
        console.log(backBlocks)
        gX = playerMoves[0]["x"]
        gY = playerMoves[0]["y"]
        backBlocks[gX+gY*xSize].innerHTML += "<div id='item'></div>"
        for (i = 1; i < playerMoves.length; i++) {
            await sleep(500)
            playerMove = playerMoves[i]
            x = playerMove["x"]
            y = playerMove["y"]
            backBlocks[gX+gY*xSize].innerHTML = ""
            gX += x
            gY += y
            console.log(x, " == ", y, "::",gX, " == ", gY, "===>", gX+gY*4)
            backBlocks[gX+gY*xSize].innerHTML += "<div id='item'></div>"
        }
    }
    
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

//Add text to Element
function addText(elementId, before, text){
    document.getElementById(elementId).value += before + text
    // text.then(
    //     text => document.getElementById(elementId).value += before + text
    // ).catch(
    //     e => document.getElementById(elementId).value += "\nERROR : " + e
    // )
    
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