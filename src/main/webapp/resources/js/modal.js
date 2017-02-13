var Modal = {
	/**
	 * 封装的ajax方法，对操作失败进行了封装处理
	 * 
	 * @param config
	 */
	ajax : function(config) {
		$.ajax({
			type : config.type || 'post',
			url : config.url,
			data : config.data,
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			async: config.async || false,
			success : function(data) {
				config.success(data);
			}
		});
	}

}
/**
 * 数组去重
 * @param arr
 * @returns {Array}
 */
function unique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    return result;
}

