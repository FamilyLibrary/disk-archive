var DataGrid = function() {
	return {
		save: function(store) {
			var jsonResult = "";
			
			store.each(function(record) {
				if (record.modified != null) {
					if (jsonResult != "") {
						jsonResult += ",";
					}
					var id = record.get("id");

					var changes = record.getChanges();
					var jsonRec = Ext.util.JSON.encode(changes);
					jsonResult += '"changes_' + id + '":' + jsonRec;
				}
			});
			
			jsonResult = "{" + jsonResult + "}";
			return jsonResult;
		}
	};
}();