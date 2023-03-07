//
////By Luca P. and Theo L. no License///
//
//Version
let appVersion = "1.2"

function setup(){
    document.getElementById("header").innerHTML += appVersion
    onClr()

    fetchElementValue("java.setup", 'metaSetup').then(
        response => initGrid(JSON.parse(response))
    ).catch(
        e => document.getElementById('outputArea').value += "\nERROR : " + e
    )

    

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

function initGrid(jsonData){
    if(jsonData != null){
        grid = jsonData["grid"];
        console.log(backBlocks, grid.length, grid)
        for(i = 0; i < grid.length; i++){
            if(grid[i] != null){
                item = document.createElement("img");
                item.classList = "item"
                item.src = grid[i];

                backBlocks[i].appendChild(item);
            }
        }
    }
    processData(jsonData)
}

//OnClicks
function onRun(){
    fetchElementValue("java.setup", 'metaSetup').then(
        response => initGrid(JSON.parse(response))
    ).catch(
        e => document.getElementById('outputArea').value += "\nERROR : " + e
    )
    fetchElementValue("java.run", 'codeArea').then(
        response => processData(JSON.parse(response))
    ).catch(
        e => document.getElementById('outputArea').value += "\nERROR : " + e
    )
    
    scrollDown('outputArea')
    clearPlayArea()
}

function onClr(){
    timePromise = "done"
    console.log("sd")
    document.getElementById('outputArea').value = "GroundZ by Luca P. and Theo L. " + appVersion
    clearPlayArea()

    fetchElementValue("java.setup", 'metaSetup').then(
        response => initGrid(JSON.parse(response))
    ).catch(
        e => document.getElementById('outputArea').value += "\nERROR : " + e
    )
}

function clearPlayArea(){
    clearPlayer()
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

timePromise = null
async function processData(jsonData){
    if(jsonData != null){
        addText('outputArea', "\nPROG : ", jsonData["textData"])
        playerMoves = jsonData["playerMoves"]
        //console.log(playerMoves)
        setPlayerPos(0, 0)
        for (i = 0; i < playerMoves.length; i++) {
            timePromise = sleep(550)
            await timePromise
            if(timePromise == "done"){
                return;
            }
            playerMove = playerMoves[i]
            //console.log(playerMove["actionName"]);
            switch(playerMove["actionName"]){
                case "move": movePlayer(playerMove["direction"]);break;
                case "collectCoin": collectCoin(); break;
            }
        }
    }
    
}

gX = 0
gY = 0
xSize = 4
backBlocks = document.getElementsByClassName("backBlock")
player = document.createElement("img");
player.classList = "item"
player.src = "Player.png"
function collectCoin(){
    backBlocks[gX+gY*xSize].innerHTML = ""
    clearPlayer();
    drawPlayer();
}

function setPlayerPos(x, y){
    clearPlayer()
    gX = x
    gY = y
    drawPlayer();
}


function movePlayer(direction){
    clearPlayer()
    x = direction["x"]
    y = direction["y"]
    console.log(x,y)
    if(Math.abs(x)+Math.abs(y) > 0){
        if(Math.abs(x) > 0){
            if(x > 0){
                player.classList.add("moveRight");
            }
            else{
                player.classList.add("moveLeft");
            }
        }
        else{
            if(y > 0){
                player.classList.add("moveDown");
            }
            else{
                player.classList.add("moveUp");
            }
        }
    }
    
    console.log(player.classList)
    drawPlayer();
    gX += x
    gY += y
}
function clearPlayer(){
    player.classList = "item"
    player.remove();
    //backBlocks[gX+gY*xSize].innerHTML = ""
}
function drawPlayer(){
    backBlocks[gX+gY*xSize].appendChild(player)
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