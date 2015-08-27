function userPrint(printId) {
	$("#" + printId).printArea({
		mode:"popup",
		popTitle:"print task list",
		popClose:false
	});
}