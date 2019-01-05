<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>商家</title>

    <style type="text/css">
        body {
            font-family: 微软雅黑;
        }

        body {
            font-size: 14px;
        }

        table {
            font-size: 14px;
        }

        table {
            border-collapse: collapse;
            border: none;
            cellspacing: 30px;
        }

        td {
            border: solid 1px #bddddd;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            height: 30px;
        }
        .td-1 .td-2 .td-3 {
            width: 33.3%;
            position: absolute;
            align-content: center;
        }

        .dialog-title {
            text-align: center;
            font-weight: bold;
            font-size: 15px;
        }
        .input-select {
            width: 120px;
        }
        .item-button {
            position: absolute;
            margin-top: -320px;
            width: 33%;
            z-index: auto;
        }
        .item-delete-button {
            width: 26px;
            height: 26px;
            margin-left: 20px;
            background:url(../assets/icon-close.png);
            border-style:none;
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }
        .item-look-button {
            width: 26px;
            height: 26px;
            float: right;
            margin-right: 20px;
            background:url(../assets/c_see@3x.png);
            border-style:none;
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }
        .item-img {
            width: 100%;
            text-align: center;
        }
        .td-row-img {
            /*width: 100%;*/
            height: 300px;
            min-width: 150px;
            max-width: 300px;
            max-height:300px;
        }
        .dialog {
            z-index: 100;
        }
    </style>

    <!-- jquery -->
    <script type="text/javascript" src="../assets/jquery-2.2.4.js"></script>

</head>

<body>

<h1>商家模型</h1>
<iframe name="handleFrame" id="handleFrame" style="display:none"></iframe>

<div>
    <div>
        <p>
            <span>商家名称: ${info["sellerName"]}</span>
            <span id="sid" style="display:none;">${info["sellerId"]}</span>
            <span style="float: right;">商家地址: ${info["sellerAddress"]}</span>
        </p>
        <p>
            <span>网站:
            <#if info["sellerWebsite"]??>
                ${info["sellerWebsite"]}
            <#else>无</#if>
            </span>
            <span style="float: right;">联系电话: ${info["sellerMobile"]}</span>
        </p>
    </div>
    <br/>
    <div>
        <span>模型展示</span>
        <span style="margin-left:50px;">条件查询:
            <select id="modelType" class="input-select">
                <option value="">全部</option>
            <#list tList as item>
                <option value="${item["typeId"]}">${item["typeName"]}</option>
            </#list>
            </select>
            <select id="modelBrand" class="input-select">
                <option value="">全部</option>
            <#if bList?exists>
                <#list bList as item>
                    <option value="${item["brandId"]}">${item["brandName"]}</option>
                </#list>
            </#if>
            </select>
            <button onclick="queryModelList();">点击查询</button>
        </span>
        <span style="float: right;">
            <input type="button" value="上传模型" onclick="openDialog();"
                   style="width:80px;height:40px;">
               <dialog close id="upload-dialog" name="upload-dialog" class="dialog">
                   <p class="dialog-title">上传模型</p>
                   <form id="addForm" name="addForm" enctype="multipart/form-data" method="post" target="handleFrame" action="../api/uploadModel">
                       <p style="display:none;">所属商家:
                           <input name="sellerId" type="text" value="${info["sellerId"]}"/>
                       </p>
                       <p>家具名称: <input name="modelName" type="text" value=""/></p>
                       <p>家具分类: <select name="modelType" class="input-select">
                           <#list tList as item>
                               <option value="${item["typeId"]}">${item["typeName"]}</option>
                           </#list>
                           </select>
                       </p>
                       <p>模型快照: <input name="modelImage" type="file" value="上传模型快照" accept="image/jpeg;image/jpg"/></p>
                       <p>模型压缩: <input name="modelFile" type="file" value="上传模型" accept=""/></p>
                       <p>家具品牌: <select name="modelBrand" class="input-select">
                           <#if bList?exists>
                               <#list bList as item>
                                   <option value="${item["brandId"]}">${item["brandName"]}</option>
                               </#list>
                           </#if>
                       </select>(可选)
                       </p>
                       <p>
                           <input type="button" value="确定" onclick="checkAndSend();" style="width:120px;height:60px;"/>
                           <input type="button" value="取消" onclick="closeDialog();" style="width:120px;height:60px;"/>
                       </p>
                   </form>
               </dialog>
            </input>
        </span>
        <table id="modelTable" name="modelTable" width="100%">
            <tr>
                <th class="td-1"></th>
                <th class="td-2"></th>
                <th class="td-3"></th>
            </tr>
        <#if list?exists>
            <#list list as item>
                <#if item_index%3==0>
                <tr>
                    <td class="td-1">
                        <div class="item-img"><img class="td-row-img" src="${item["imageUrl"]}"></img></div>
                        <div>名称: ${item["modelName"]} <span style="float:right;"><a href="${item["fileUrl"]}">下载查看</a></span></div>
                        <div>分类: ${item["typeName"]}</div>
                        <div class="item-button">
                            <button type="button" class="item-delete-button" onclick="deleteModel('${item["modelId"]}');"></button>
                            <button type="button" class="item-look-button" onclick="lookModel('${item["modelId"]}');"></button>
                        </div>
                    </td>
                <#elseif item_index%3==1>
                    <td class="td-2">
                        <div class="item-img"><img class="td-row-img" src="${item["imageUrl"]}"></img></div>
                        <div>名称: ${item["modelName"]} <span style="float:right;"><a href="${item["fileUrl"]}">下载查看</a></span></div>
                        <div>分类: ${item["typeName"]}</div>
                        <div class="item-button">
                            <button type="button" class="item-delete-button" onclick="deleteModel('${item["modelId"]}');"></button>
                            <button type="button" class="item-look-button" onclick="lookModel('${item["modelId"]}');"></button>
                        </div>
                    </td>
                <#else>
                    <td class="td-3">
                        <div class="item-img"><img class="td-row-img" src="${item["imageUrl"]}"></img></div>
                        <div>名称: ${item["modelName"]} <span style="float:right;"><a href="${item["fileUrl"]}">下载查看</a></span></div>
                        <div>分类: ${item["typeName"]}</div>
                        <div class="item-button">
                            <button type="button" class="item-delete-button" onclick="deleteModel('${item["modelId"]}');"></button>
                            <button type="button" class="item-look-button" onclick="lookModel('${item["modelId"]}');"></button>
                        </div>
                    </td>
                </tr>
                </#if>
            </#list>
        </#if>
        </table>
    </div>
</div>

</body>

<script type="text/javascript">
    function openDialog() {
        document.getElementById("upload-dialog").show();
    }
    function checkAndSend() {
        document.getElementById("addForm").submit();
    }
    function closeDialog() {
        document.getElementById("upload-dialog").close();
    }
    function showResult(result) {
        if ('success' == result) {
            location.reload(true);
        } else {
            alert(result);
        }
    }
    function deleteModel(modelId) {
        var r = confirm("是否确认删除?")
        if (r == true) {
            // 发起ajax请求
            var htmlobj = $.ajax({url:"../api/deleteModel?modelId="+modelId, async:false});
            var respJson = JSON.parse(htmlobj.responseText);
            if (respJson.code == 200) {
                location.reload(true);
                alert("删除成功");
            } else {
                alert(htmlobj.responseText);
            }
        }
    }
    function lookModel(modelId) {
        window.open("../seller/model_share?mid=" + modelId);
    }
    function queryModelList() {
        var sellerId = document.getElementById("sid").innerText;
        var typeId = document.getElementById("modelType").value;
        var brandId = document.getElementById("modelBrand").value;
        // 发起ajax请求
        var url = "../seller/model_list?typeId=" + typeId + "&brandId=" + brandId + "&sellerId=" + sellerId;
        var htmlobj = $.ajax({url:url, async:false});
        var respJson = JSON.parse(htmlobj.responseText);
        if (respJson.code == 200) {
            var items = respJson.data;
            // table局部刷新
            var htmlString = "<tr><th class='td-1'></th><th class='td-2'></th><th class='td-3'></th></tr>";
            var n = items.length/3;
            if (items.length%3 > 0) {
                n += 1;
            }
            for (var i=0; i<n; i++) {
                htmlString += "<tr>";
                var k = items.length - i*3;
                if (k >= 3) {
                    k = 3;
                }
                for (var j=1; j<=k; j++) {
                    var item = items[i*3 + j-1];
                    htmlString += "<td class='td-" + j + "'>";
                    htmlString += "<div class='item-img'><img class='td-row-img' src='" + item.imageUrl + "'></img></div>";
                    htmlString += "<div>名称: " + item.modelName + "<span style='float:right;'><a href='" + item.fileUrl + "'>下载查看</a></span></div>";
                    htmlString += "<div>分类: " + item.typeName + "</div>";
                    htmlString += "<div class='item-button'>";
                    htmlString += "<button type='button' class='item-delete-button' onclick='deleteModel(" + item.modelId + ");'></button>";
                    htmlString += "<button type='button' class='item-look-button' onclick='lookModel(" + item.modelId + ");'></button>";
                    htmlString += "</div>";
                    htmlString += "</td>";
                }
                htmlString += "</tr>";
            }
            document.getElementById("modelTable").innerHTML = htmlString;
        }
    }
</script>

</html>