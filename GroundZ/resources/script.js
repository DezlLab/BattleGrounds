//Global State of Buttons and Functions
// Not used var global_States = [0,0,0];

//Load Content of Json into Position
function start()
{
	if(0==getStorageLen())//Load Default
	{
		setStorage("Future Import Files;", 0);	//getStorage returns >> : LucaMNS;
		setStorage("20.1", 1);		//getStorage returns >> : 20.1
		setStorage("#1e5b76", 2);	//getStorage returns >> : #1e5b76
		setStorage("#85d5a1", 3);	//getStorage returns >> : #85d5a1
		setStorage("#11322e", 4);	//getStorage returns >> : #11322e
		setStorage("#00ffb7", 5);	//getStorage returns >> : #00ffb7
		setStorage("#fa4b00", 6);	//getStorage returns >> : #fa4b00
		setStorage("280", 7);		//getStorage returns >> : 200
		setStorage("#113c37", 8);	//getStorage returns >> : #113c37
		setStorage("#d1ffa3", 9);	//getStorage returns >> : #d1ffa3
	}
	updateJS();
	updateHTML();
	updateCSS();
	console.log(localStorage);
	//var consol = document.getElementById("S_Console");
	//Console Input Event
	var C_Input = document.getElementsByClassName("S_C_Edit")[0];
	C_Input.addEventListener("keyup", function(event) {
		
		if(event.keyCode == 13)
		{
			addToConsole("User Input", C_Input.value, "t");
			C_Input.value = ""; //Prepar for new Entry
		}
	});
	//"<object data='defaultValues.json' type='application/json' class='S_C_Text'></object>";
}

//update Preferences Values
function updateHTML()
{
	// if(0==getStorageLen())//Load Default
	// {
		// setStorage("myMNStack;", 0);
		// setStorage("17.5", 1);
		// setStorage("#ff0000", 2);
		// setStorage("#000000", 3);
		// setStorage("#000000", 4);
		// setStorage("#000000", 5);
		// setStorage("#000000", 6);
		// setStorage("#000000", 7);
		// setStorage("#000000", 8);
		// setStorage("#000000", 9);
	// }
	var preferences = document.getElementsByClassName("M_Preferences");
	for(var i = 0; i < preferences.length; i++)
	{
		preferences[i].value = getStorage(i);
	}
}

//update JS/Look
function updatePreferences(index)
{
	var inputList = document.getElementsByClassName("M_Preferences");
	setStorage(inputList[index].value, index);
	if(0 == index)
	{
		updateJS();	
		updateHTML();
	}
	else
	{
		updateCSS();
	}
}

//update CSS
function updateCSS()
{
	//var root = document.querySelector(':root');
	var root = document.documentElement;
	var preferences = document.getElementsByClassName("M_Preferences");
	for(var i = 1; i < preferences.length; i++)//1 because is only for JS
	{
			root.style.setProperty("--ID"+i, getStorage(i));
	}
}

//update JS
function updateJS()
{
	var body = document.body;
	var scripts = body.getElementsByTagName('script');//All Scripts able to Remove
	while(0 != scripts.length)
	{
			//console.log(scripts);
		scripts[0].remove();
	}
	var scriptsName = getStorage(0).split(";");
	scriptsName.pop();
		//console.log(scriptsName);
	for(var i = 0; i < scriptsName.length; i++)
	{
		scriptsName[i] = scriptsName[i].replaceAll(" ", "");
		scriptsName[i] = scriptsName[i].replaceAll("init", "");
		scriptsName[i] = scriptsName[i].replaceAll(".js", "");
			console.log(scriptsName[i]);
		addJS("data/init"+scriptsName[i]+".js");
			//console.log(init(scriptsName[i]));
	}
}

//Add JavaScript
function addJS(fileName)
{
	var newSrc = document.createElement("script");
	newSrc.setAttribute("type", "text/javascript");
	newSrc.setAttribute("src", fileName);
	var body = document.body;
	body.appendChild(newSrc);
	
	addToConsole("func addJS", fileName, "f");
}

//Make a Call to any Function with one Input !!!!
function funcCall(funcName, input)
{
		console.log(input);
	try
	{
		var res = window[funcName](input);
		addToConsole(funcName+" returns >> ", res, "r");
	}
	catch(err)
	{
		addToConsole(funcName+" fatal Error", err.message, "!");
	}
}

//Process Inputs form .html
function state(id)
{
	//console.log(id)
	switch(id[0])
	{
		case 'E': updateActivTab("S_E_Button", "S_E_Options", id[1], 0);break;//StackEdit
		case 'C': updateActiv("S_C_Toggle", "S_Console", id[1]);break;//Toggle Console
		case 'M': updateActiv("M_Toggle", "Menu", id[1]);break;//Menu/Toolbar
		case 'P': updatePreferences(id[1]);break;//User Preferences
		case 'F': saveLoadFile(id[1]);//Load/Save File
	}
}

//Get Methodes
function setStorage(data, index)//TODO new when in App
{
	localStorage.setItem("ID:"+index, data);
}
function getStorage(index)
{
	return localStorage.getItem("ID:"+index);
}
function getStorageLen()
{
	return localStorage.length;
}
function getText()
{
	var textObj = document.getElementsByClassName("S_Text")[0];
	return textObj.value
}
function setText(str)
{
	var textObj = document.getElementsByClassName("S_Text")[0];
	textObj.value = str;
}

//Update Look Buttons
function updateActiv(objsID, targetObj, index)
{
	var object = document.getElementsByClassName(objsID);
		console.log("updateActiv:", objsID, index, object);
	var S_E_Options = document.getElementsByClassName(targetObj);
		//console.log(S_E_Options[index].classList.contains("hiddenObj"));
	if(S_E_Options[index].classList.contains("hiddenObj"))
	{	
		object[index].classList.add("active");
		S_E_Options[index].classList.remove("hiddenObj"); 
		S_E_Options[index].classList.add("visibleObj");
	}
	else
	{
		object[index].classList.remove("active"); 
		S_E_Options[index].classList.remove("visibleObj"); 
		S_E_Options[index].classList.add("hiddenObj");
	}
}

//Test the callFunc functions Circle
function conny(i)
{
		console.log(i);
	return i[0]*i[2];
}
function test()
{
	return "Hello it works :)";//Test the callFunc functions Circle
}

//Console 
function addToConsole(from, str, msgType)
{
	var type = "";
	var cText = document.getElementsByClassName("S_C_Text")[0];
	var message = document.createElement("div");
	if(str[0] == "f" && str[1] == ":")
	{
		msgType = "f";
	}
	switch (msgType)
	{
		case"!": type += "error";break;//Error
		case"f": type += "func";break;//Function
		case"r": type += "func";break;//Function return 
		case"t": type = "";//Text is default = #fff
	}
	message.setAttribute("id", type);
	message.innerText =  from + " : " + str;
	cText.appendChild(message);
	cText.scrollTo(0, cText.scrollHeight);
	if(msgType=="f")
	{
		stringToCall(str);
			//console.log("Nice");
	}

}

//Convert Console Text to Call
function stringToCall(str)
{
		//console.log("Test:::",str);
	str = str.replace("f:", "");
	str = str.replaceAll(" ", "");
	str = str.replaceAll("()", ";");
	str = str.replaceAll("(", ";");
	str = str.replaceAll(")", ";");
	str = str.split(";");
		console.log(str);
	if(str.length%3 != 0)
	{
		str.pop();
	}
		console.log("after pop:",str);
	for(var i = 0; i < str.length; i+=2)
	{
		funcCall(str[i], str[i+1]);
	}
	
}
//Clear Console
function cls()
{
	var cText = document.getElementsByClassName("S_C_Text")[0];
	cText.innerHTML = "";
}
function saveLoadFile(index)
{
	console.log("sLF: ", index);
	if(index==0)
	{
		var str = getText();
		var fileName = "name";
		var save = document.createElement('a');
		save.setAttribute('href', 'data:text/plain;charset=utf-8,' + 

		encodeURIComponent(str));
		save.setAttribute('download', fileName);

		save.style.display = 'none';
		document.body.appendChild(save);

		save.click();

		document.body.removeChild(save);
	}
	else
	{
		var load = document.createElement('input');
		load.setAttribute('type', "file")
		document.body.appendChild(load);
		load.click();
		console.log("Load",load);
		document.body.removeChild(load);
	}
}



//New


var seperators = document.getElementsByClassName("splitter")
for(var i = 0; i < seperators.length; i++){
	console.log()
	if(getComputedStyle(seperators[i]).getPropertyValue("cursor")=="col-resize"){
		dragElement( seperators[i], "H")
	}
	else{
		dragElement( seperators[i], "V")
	}

	function dragElement(splitter, direction)
	{
		splitter.onmousedown = onMouseDown;
		
		var id = i
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
			var fontSize = getStorage(1)
			var handel = 10 //TODO redo storage
			if(val < fontSize || curPos < 0){val = 0}
			if(val > max - fontSize - handel){val = max - handel}
				document.documentElement.style.setProperty("--Splt"+id, val+"px");
			//console.log("mouse move: " + e.clientX);
		}
	}
}

var stack = document.getElementById("_stack");
function addStackNode(addButton)
{
	//var stack = addButton.parentElement;
	var node = newNode()
	stack.prepend(node)
}

function newNode(){
	var node = document.createElement("div");
	node.setAttribute("class", "S_Node");
	node.innerHTML =   "<button onclick='deleteNode(this)' class='Node_Delete'>X</button>"+
						"<select class='Node_Select'>"+
							"<option value='0'>findeDo</option>"+
							"<option value='1'>Type</option>"+
							"<option value='2'>Abc</option>"+
						"</select>"+
						"<button class='Node_Container'>Test</button>";
	return node;
}

function deleteNode(node){
	stack.removeChild(node.parentElement);
}