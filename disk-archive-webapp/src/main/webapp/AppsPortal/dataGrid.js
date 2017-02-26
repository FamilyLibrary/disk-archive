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
					
					var obj = {};
					//var meta = {};
					
					obj["id"] = id;
					//meta["id"] = id;

					record.fields.each(function(field) {
						var name = field.name;
						if (record.modified[name] != undefined) {
							var value = record.get(name);
							
							if (field.mapping != null) {
								name = field.mapping;
							}
							var type = typeof value;

							obj[name] = value;
							//meta["type"] = type;
						}
					});

					var jsonRec = Ext.util.JSON.encode(obj);
					//var metaRec = Ext.util.JSON.encode(meta);
					
					jsonResult += '"changes_' + id + '":' + jsonRec;
					//jsonResult += ',"meta":' + metaRec;
				}
			});
			
			jsonResult = "{" + jsonResult + "}";
			return jsonResult;
		}
	};
}();