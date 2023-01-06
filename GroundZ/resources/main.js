//
////By Luca P. and Theo L. no License///
//
//Version
let appVersion = "0.98B"

function setup(){
    //clear()
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