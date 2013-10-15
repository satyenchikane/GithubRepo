<html>
<head>

<script type="text/javascript">

var changeWatcher = {
    timeout: null,
    currentValue: '',
    watchForChange: function( el ) {
  //  	alert("el.value="+el.value + " this.currentValue=" + this.currentValue); 
        if (el.value != ''){
	        if( el.value != this.currentValue ) {
	            this.changed( el );
	        }
		}
        this.timeout = setTimeout( function() {
             changeWatcher.watchForChange(el)
        }, 200 );
    },
    cancelWatchForChange: function() {
        clearTimeout( this.timeout );
        this.timeout = null;
    },
    changed: function( el ) {
        this.currentValue = el.value;
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.open("POST","<%=request.getContextPath()%>/WordGameServlet?wordinput="+document.gameform.wordinput.value,false);
		xmlHttp.send();

		var gamestatus = xmlHttp.responseText.charAt(0);
		var wordinputvalue = xmlHttp.responseText.substring(1);
		document.getElementById("wordinput").value = xmlHttp.responseText.substring(1);
		this.currentValue=document.gameform.wordinput.value;

		if(gamestatus == 1)
			gamestatusdiv.innerHTML = 'Play on....';
		if(gamestatus == 2)
			gamestatusdiv.innerHTML = 'Player lose... :(';
		if(gamestatus == 3)
			gamestatusdiv.innerHTML = 'Player won.... :)';
		if(gamestatus == 4)
			gamestatusdiv.innerHTML = 'Player lose, no such word.... :( ';
    }
}
</script>
<script type="text/javascript">
	function clearTextbox(){
		document.gameform.wordinput.value = "";
	}
</script>

</head>
<body>
<h2>Welcome to WordComplete... </h2>
    <p>a game where you dont want to complete a word...</p>
    <p>                becasue if you did, you lose!!!!</p>

<h2>How to play and game rules</h2>
<ul>
<li> The player and the computer take turns building up an English word from left to right, each adding a letter </li>
<li> Player loses if he/she add a letter that either completes a word or produces a string that is not or cannot be extended into a word </li>
</ul>


<h2>Start Game</h2>


<form name="gameform" action="<%=request.getContextPath()%>/WordGameServlet" method="GET" >
	<dl class=" " id="label_field">
	    <dt><label for="label">Enter letter</label></dt>
	    <dd>
		<% String textboxvalue = (String)request.getAttribute("wordinput");
			if (textboxvalue == null)
				textboxvalue = "";
		 %>

		<input type="text" id="wordinput" name="wordinput" value="<%=textboxvalue%>"  onfocus='changeWatcher.watchForChange(this)' onblur='changeWatcher.cancelWatchForChange()'  /> 
		</dd>
	</dl>
	
	<div id="gamestatusdiv"> </div>
	        <input type="button" value="Start again" onclick=clearTextbox();>
</form>
</body>
</html>
