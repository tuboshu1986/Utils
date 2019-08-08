var ManagerGlobalUtilsType = (function(){
    var Type = function(){

    };

    Type.prototype.appendToStr = function(baseStr, currStr, splitChar){
        if(!currStr)
            return baseStr;
        var str = [];
        splitChar = splitChar ? splitChar : ",";
        if(baseStr){
            str = str.concat(baseStr.split(splitChar));
        }
        str.push(currStr);
        return str.join(splitChar);
    };

    Type.prototype.getValueFromObject = function(obj, key){
        if(!key || !obj)
            return null;
        var keys = key.split(".");
        var curr = obj;
        for(i = 0; i < keys.length; i++){
            curr = curr[keys[i]];
            if(!curr)
                break;
        }
        return curr ? curr: null;
    };

    Type.prototype.clearNullProperties = function(obj){
        if(!obj)
            return;
        var nullKeys = [];
        for(key in obj){
            if(obj[key])
                continue;
            nullKeys.push(key);
        }
        nullKeys.forEach(function(item){
            delete obj[item];
        });
    };

    /**
     * 将from中与to同名且不为空的属性值赋值到to中，只遍历第一层
     * @param from
     * @param to
     */
    Type.prototype.copyProperties = function(from, to){
        for(key in to){
            if(from[key] && (typeof from[key] != "object")){
                to[key] = from[key];
            }
        }
    };

    /**
     * 将from中与to同名且不为空的属性值赋值到to中，遍历所有层，包括数组
     * @param from
     * @param to
     */
    Type.prototype.copyPropertiesThorough = function(from, to){
        var target = this;
        for(key in to){
            if(Array.isArray(to[key]) && from[key] && Array.isArray(from[key])){
                target.copyPropertiesThoroughForArray(from[key], to[key]);
            }else if((typeof to[key] == "object") && from[key] && (typeof from[key] == "object")){
                this.copyPropertiesThorough(from[key], to[key]);
            }else if(from[key]){
                to[key] = from[key];
            }
        }
    };

    Type.prototype.copyPropertiesThoroughForArray = function(from, to){
        var target = this;
        to.forEach(function(item, index){
            if(Array.isArray(item) && from[index] && Array.isArray(from[index])){
                target.copyPropertiesThoroughForArray(from[index], item);
            }else if((typeof item == "object") && from[index] && (typeof from[index] == "object")){
                target.copyPropertiesThorough(from[index], item);
            }
        });
    };

    /**
     * 从obj中获取键为key的值，如果key的样式如"key1.key2.key3"，返回obj[key1][key2][key3]的值
     * @param obj
     * @param key
     */
    Type.prototype.objectVal = function(obj, key){
        var keys = key.split(".");
        var currObj = obj;
        for(i in keys){
            if(currObj[keys[i]])
                currObj = currObj[keys[i]];
            else
                breal;
        }
        return currObj === obj ? null : currObj;
    }

    Type.prototype.toSpringMVCParams = function(){

    }

    return Type;
})();



var ManagerGlobalDataUtilsType = (function(){
    var Type = function(){};

    Type.prototype.postAndShowMsg = function (url, params, msg, callback) {
        this.post(url, params, function(obj, resp){
            ManagerGlobalUI.message(msg ? msg : (obj ? obj : "处理完成"));
            if(callback)
                callback(obj);
        });
    };

    Type.prototype.post = function (url, params, callback) {
        var target = this;
        $.ajax(url, {
            type:"POST",
            data:params,
            dataType:"json",
            error:function(){
                ManagerGlobalUI.error("发送数据至服务器时出现错误");
            },
            success:function(resp){
                target.respIsValid(resp, callback);
            }
        });
    };

    Type.prototype.postJsonBody = function (url, params, callback) {
        var target = this;
        $.ajax(url, {
            type:"POST",
            data:JSON.stringify(params),
            //dataType:"json",
            beforeSend:function(request){
                request.setRequestHeader("Content-Type","application/json; charset=utf-8");
            },
            error:function(){
                ManagerGlobalUI.error("发送数据至服务器时出现错误");
            },
            success:function(resp){
                target.respIsValid(resp, callback);
            }
        });
    };

    /**
     * 判断返回报文的状态是否是成功，如果是则回调，否则弹出错误消息
     * @param resp
     * @param callback
     */
    Type.prototype.respIsValid = function(resp,callback){
        if(resp){
            if((typeof resp) == "string")
                resp = JSON.parse(resp);
            if(resp.success){
                if(callback)
                    callback(resp.obj);
                return;
            }
            ManagerGlobalUI.error(resp.msg ? resp.msg : "出现错误");
            return;
        }
        ManagerGlobalUI.error("出现错误");
    }


    return Type;
})();



var ManagerGlobalUIType = (function(){
    var Type = function(){};

    Type.prototype.message = function (msg, callback) {
        alert(msg);
        if(callback)
            callback();
    };

    Type.prototype.error = function(msg, callback){
        alert(msg);
        if(callback)
            callback();
    };

    Type.prototype.confirm = function(question, callback){
        if(confirm(question)){
            if(callback)
                callback();
        }
    };

    return Type;
})();



var InitCascadeSelectType = function(dataUrls, selectors){
    selectors.forEach(function(elem, index, arr){
        if(index < selectors.length - 1)
            $(elem).attr("data-nextselector", arr[index + 1]);

        if(dataUrls.length == selectors.length){
            $(elem).attr("data-currurl", dataUrls[index]);
        }else{
            $(elem).attr("data-currurl", dataUrls[0]);
        }

        if($(elem).find("option[value='']").length == 0){
            $(elem).append($("<option value=''>" + (index + 1) + "级分类</option>"));
        }
    });

    function clearChildrenOption(selector){
        var nullOpt = $(selector).children("[value='']");
        $(selector).children("option").remove();
        if(nullOpt){
            $(selector).append(nullOpt);
        }
        if($(selector).data("nextselector"))
            clearChildrenOption($(selector).data("nextselector"));
    }

    function bindChange(selector){
        $(selector).off("change");
        $(selector).on("change", function(){
            var curr = $(selector);
            if(!curr.val()){
                if($(selector).data("nextselector"))
                    clearChildrenOption($(selector).data("nextselector"));
                return;
            }
            if($(selector).data("nextselector"))
                initSelect(curr.val(), $(selector).data("nextselector"));
        });
        var initValue = $(selector).data("initvalue");
        if(initValue){
            $(selector).val(initValue);
            $(selector).change();
            $(selector).data("initvalue", "");
        }
    }

    function initSelect(id, selector){
        if(!id)
            id = "";
        var url = $(selector).data("currurl");
        $.get(url + id, function(resp){
            if(!resp.success){
                alert("下一级数据获取失败");
                return;
            }
            clearChildrenOption(selector);
            resp.obj.forEach(function(elem, index){
                $(selector).append($("<option value='" + elem.id + "'>" + elem.categoryName + "</option>"));
            })
            bindChange(selector);
        }, "json");
    }

    initSelect(null, selectors[0]);
};





var ManagerGlobalUI = (function() {
    return new ManagerGlobalUIType();
})();

var ManagerGlobalUtils = (function(){
    return new ManagerGlobalUtilsType();
})();

var ManagerGlobalPlugin = {
    initCascadeSelect: InitCascadeSelectType
};

var ManagerGlobalDataUtils = (function(){
    return new ManagerGlobalDataUtilsType();
})();