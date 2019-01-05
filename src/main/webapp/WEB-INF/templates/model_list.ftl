<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>模型列表</title>

    <style type="text/css">
        body {
            font-family: 微软雅黑;
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
        .td-1 .td-2 .td-3 .td-4 .td-5 {
            position: absolute;
            align-content: center;
            height: 60px;
        }
        .td-6 {
            width: 140px;
            height: 60px;
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
            height: 60px;
        }
        .dialog {
            z-index: 100;
        }
        .btn-click-1 {
            margin-left: 20px;
        }
        .btn-click-2 .btn-click-3 {
            margin-left: 10px;
        }

        .input-button {
            -webkit-appearance: button;
        }
    </style>

    <!-- jquery -->
    <script type="text/javascript" src="../assets/jquery-2.2.4.js"></script>

</head>

<body>

<h1>模型列表</h1>
<iframe name="handleFrame" id="handleFrame" style="display:none"></iframe>

<div>
    <div>
        <span>模型展示</span>
        <span style="margin-left:50px;">条件查询:
            <select id="modelSeller" class="input-select">
                <option value="">全部商家</option>
                 <#if sList?exists>
                    <#list sList as item>
                       <option value="${item["sellerId"]}">${item["sellerName"]}</option>
                    </#list>
                </#if>
            </select>
            <select id="modelType" class="input-select">
                <option value="">全部分类</option>
            <#list tList as item>
                <option value="${item["typeId"]}">${item["typeName"]}</option>
            </#list>
            </select>
            <select id="modelBrand" class="input-select">
                <option value="">全部品牌</option>
            <#if bList?exists>
                <#list bList as item>
                    <option value="${item["brandId"]}">${item["brandName"]}</option>
                </#list>
            </#if>
            </select>
            <button onclick="queryModelList();">点击查询</button>
        </span>
        <span style="float: right;">
            <input type="button" class="input-button" value="上传模型" onclick="openDialog();"
                   style="width:80px;height:22px;">
               <dialog close id="upload-dialog" name="upload-dialog" class="dialog">
                   <p class="dialog-title">上传模型</p>
                   <form id="addForm" name="addForm" enctype="multipart/form-data" method="post" target="handleFrame" action="../api/uploadModel">
                       <p>所属商家:
                           <select name="sellerId" id="modelSellerDlg" class="input-select">
                           <#if sList?exists>
                               <#list sList as item>
                                   <option value="${item["sellerId"]}">${item["sellerName"]}</option>
                               </#list>
                           </#if>
                           </select>
                       </p>
                       <p>家具名称: <input name="modelName" type="text" value=""/></p>
                       <p>家具分类: <select name="modelType" class="input-select">
                       <#list tList as item>
                           <option value="${item["typeId"]}">${item["typeName"]}</option>
                       </#list>
                       </select>
                       </p>
                       <p>模型快照: <input name="modelImage" type="file" value="上传模型快照" accept="image/jpeg"/></p>
                       <p>模型压缩: <input name="modelFile" type="file" value="上传模型" accept=""/></p>
                       <p>家具品牌: <select name="modelBrand" class="input-select">
                       <#if bList?exists>
                           <#list bList as item>
                               <option value="${item["brandId"]}">${item["brandName"]}</option>
                           </#list>
                       </#if>
                       </select> (可选)
                       </p>
                       <p style="text-align:center;margin-top:20px;">
                           <input type="button" class="input-button" value="确定" onclick="checkAndSend();" style="width:120px;height:25px;"/>
                           <input type="button" class="input-button" value="取消" onclick="closeDialog();" style="width:120px;height:25px;margin-left:30px;"/>
                       </p>
                   </form>
               </dialog>
            </input>
        </span>
        <br/><br/>
        <table id="modelTable" name="modelTable" width="100%">
            <tr>
                <th class="td-0">模型id</th>
                <th class="td-1">模型名称</th>
                <th class="td-2">模型分类</th>
                <th class="td-3">模型品牌</th>
                <th class="td-4">更新时间</th>
                <th class="td-5">模型快照</th>
                <th class="td-6">操作</th>
            </tr>
        <#if list?exists>
            <#list list as item>
                <tr>
                    <td class="td-0">
                    ${item["modelId"]}
                    </td>
                    <td class="td-1">
                        ${item["modelName"]}
                    </td>
                    <td class="td-2">
                        ${item["typeName"]}
                    </td>
                    <td class="td-3">
                        ${item["brandName"]}
                    </td>
                    <td class="td-4">
                        ${item["updateTime"]}
                    </td>
                    <td class="td-5">
                        <div class="item-img"><img class="td-row-img" src="${item["imageUrl"]}"></img></div>
                    </td>
                    <td class="td-6">
                        <button type="button" class="btn-click-1" onclick="lookModel('${item["modelId"]}');">查看</button>
                        <button type="button" class="btn-click-2" onclick="deleteModel('${item["modelId"]}');">删除</button>
                        <button type="button" class="btn-click-3" onclick="downModel('${item["fileUrl"]}');">下载</button>
                    </td>
                </tr>
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
        let url = "../seller/model_share?mid=" + modelId;
        window.open(url);
    }
    function downModel(url) {
        window.open(url);
    }

    function queryModelList() {
        var sellerId = document.getElementById("modelSeller").value;
        var typeId = document.getElementById("modelType").value;
        var brandId = document.getElementById("modelBrand").value;
        // 发起ajax请求
        var url = "../seller/model_list?typeId=" + typeId + "&brandId=" + brandId + "&sellerId=" + sellerId;
        var htmlobj = $.ajax({url:url, async:false});
        var respJson = JSON.parse(htmlobj.responseText);
        if (respJson.code == 200) {
            var items = respJson.data;
            // table局部刷新
            var htmlString = "<tr> \
                <th class='td-0'>模型id</th> \
                <th class='td-1'>模型名称</th> \
                <th class='td-2'>模型分类</th> \
                <th class='td-3'>模型品牌</th> \
                <th class='td-4'>更新时间</th> \
                <th class='td-5'>模型快照</th> \
                <th class='td-6'>操作</th> \
                </tr>";
            for (var i=0; i<items.length; i++) {
                var item = items[i];
                //
                htmlString += "<tr>";
                htmlString += "<td class='td-0'>" + item.modelId + "</td>";
                htmlString += "<td class='td-1'>" + item.modelName + "</td>";
                htmlString += "<td class='td-2'>" + item.typeName + "</td>";
                htmlString += "<td class='td-3'>" + item.brandName + "</td>";
                htmlString += "<td class='td-4'>" + item.updateTime + "</td>";
                htmlString += "<td class='td-5'><div class='item-img'><img class='td-row-img' src='" + item.imageUrl + "'></img></div></td>";
                htmlString += "<td class='td-6'>" +
                        "<button type='button' class='btn-click-1' onclick='lookModel(\"" + item.modelId + "\");'>查看</button>" +
                        "<button type='button' class='btn-click-2' onclick='deleteModel(\"" + item.modelId + "\");'>删除</button>" +
                        "<button type='button' class='btn-click-3' onclick='downModel(\"" + item.fileUrl + "\");'>下载</button>" +
                        "</td>";
                htmlString += "</tr>";
            }
            document.getElementById("modelTable").innerHTML = htmlString;
        }
    }
</script>

</html>