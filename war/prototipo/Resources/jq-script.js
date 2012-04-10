

google.load("jquery", "1");

function OnLoad(){
    $(function(){
    	var idList = new Array();
    	i=0;
    	$("#content .Page").hide();
    	$("#content").find("div.Page").each(
    		function() {
    			idList[i++] = this.id;
    		}
    			
    	);
    	
    	var counter=0;
    	$("#"+idList[0]).show();
    	
    	$("#nextLink").click(
    		function() {
    	
    			if (counter < ( idList.length - 1) ) {
    				
    				var current = "#"+idList[counter];
        			var next = "#"+idList[counter+1];
    				counter++;
    				$(current).hide();
    				$(next).show();
    			}
    			
    		}
    	);
    	$("#prevLink").click (
			function() {
				if (counter>0) {
    				var current = "#"+idList[counter];
        			var next = "#"+idList[counter-1];
    				counter--;
    				$(current).hide();
    				$(next).show();
				}
			}
    	);
    	
    });
}

google.setOnLoadCallback(OnLoad);
