<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>分类管理</title>

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

        .td-id {
            width: 40px;
            max-width: 40px;
            text-align: center;
        }

        .td-name {
            width: 120px;
            max-width: 120px;
        }

        .td-desc {
            width: 150px;
            max-width: 150px;
        }

        .td-time {
            width: 150px;
            max-width: 120px;
        }

        .td-parent {
            width: 80px;
            max-width: 80px;
            text-align: center;
        }

        .button-add {
            width: 120px;
            height: 44px;
            float: right;
            margin-top: -10px;
            margin-bottom: 10px;
        }
        .dialog-title {
            text-align: center;
            font-weight:bold;
            font-size: 15px;
        }
        .input-select {
            width: 120px;
        }
    </style>

    <script type="text/javascript">
        function openDialog() {
            document.getElementById("add-dialog").show();
        }
        function checkAndSend() {
            document.getElementById("addForm").submit();
        }
        function closeDialog() {
            document.getElementById("add-dialog").close();
        }
        function showResult(result) {
            if ('success' == result) {
                location.reload(true);
            } else {
                alert(result);
            }
        }
        function editType(typeId) {

        }
        function deleteType(typeId) {
            var r = confirm("是否确认删除该分类?\n\n提示: 删除该分类后,该分类下所有的模型也将会被删除");
            if (r == true) {
                // 发起ajax请求
                var url = "../api/deleteType?typeId=" + typeId;
                var htmlobj = $.ajax({url:url, async:false});
                var respJson = JSON.parse(htmlobj.responseText);
                if (respJson.code == 200) {
                    location.reload(true);
                    alert("删除成功");
                } else {
                    alert(htmlobj.responseText);
                }
            }
        }
    </script>

</head>

<body>

<h1>家具分类管理</h1>
<iframe name="handleFrame" id="handleFrame" style="display:none"></iframe>

<div>
    <input type="button" value="增加分类" onclick="openDialog();" class="button-add">
    <dialog close id="add-dialog" name="add-dialog">
        <p class="dialog-title">添加分类</p>
        <form id="addForm" name="addForm" method="post" target="handleFrame" action="../api/addType">
            <p>分类名称: <input name="typeName" type="text" value=""/></p>
            <p>分类简介: <input name="typeDesc" type="text" value=""/></p>
            <p>父级分类:
                <select name="parentType" class="input-select">
                    <option value="0">暂无</option>
                </select>
            </p>
            <p>
                <input type="button" value="确定" onclick="checkAndSend();" style="width:120px;height:60px;"/>
                <input type="button" value="取消" onclick="closeDialog();" style="width:120px;height:60px;"/>
            </p>
        </form>
    </dialog>
    </input>
    <table id="msgTable" name="msgTable" width="100%">
        <tr>
            <th class="td-id">分类id</th>
            <th class="td-name">分类名称</th>
            <th class="td-desc">描述</th>
            <th class="td-time">创建时间</th>
            <th class="td-parent">父级分类</th>
            <th class="td-oper">操作</th>
        </tr>
    <#if list?exists>
        <#list list as item>
            <tr>
                <td class="td-id">${item["typeId"]}</td>
                <td class="td-name">${item["typeName"]}</td>
                <td class="td-desc">${item["typeDesc"]}</td>
                <td class="td-time">${item["createTime"]}</td>
                <td class="td-parent">${item["parentType"]}</td>
                <td class="td-oper">
                    <a href="#" onclick="editType(${item["typeId"]});">编辑</a>
                    <a href="#" onclick="deleteType(${item["typeId"]});">删除</a>
                </td>
            </tr>
        </#list>
    </#if>
    </table>
</div>

</body>

</html>