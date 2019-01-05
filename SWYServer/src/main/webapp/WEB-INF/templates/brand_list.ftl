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

        .td-logo {
            width: 80px;
            max-width: 80px;
            text-align: center;
        }

        .td-desc {
            width: 150px;
            max-width: 150px;
        }

        .td-time {
            width: 120px;
            max-width: 120px;
        }

        .td-update {
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
        .input-p {
            width: 200px;
        }

        .img-logo {
            width: 60px;
            height: 60px;
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
    </script>

</head>

<body>

<h1>家具品牌管理</h1>
<iframe name="handleFrame" id="handleFrame" style="display:none"></iframe>

<div>
    <input type="button" value="新增品牌" onclick="openDialog();" class="button-add">
    <dialog close id="add-dialog" name="add-dialog">
        <p class="dialog-title">新增品牌</p>
        <form id="addForm" name="addForm" method="post" enctype="multipart/form-data" target="handleFrame" action="../api/addBrand">
            <p>品牌名称: <input name="brandName" type="text" value="" class="input-p"/></p>
            <p>品牌LOGO: <input name="brandLogo" type="file" value="上传品牌LOGO图" accept="image/gif, image/jpg, image/jpeg, image/png"/></p>
            <p>品牌故事: <textarea name="brandDesc" value="" rows="3" cols="30"></textarea></p>
            <p>
                <input type="button" value="确定" onclick="checkAndSend();" style="width:120px;height:60px;"/>
                <input type="button" value="取消" onclick="closeDialog();" style="width:120px;height:60px;margin-left:20px;"/>
            </p>
        </form>
    </dialog>
    </input>
    <table id="msgTable" name="msgTable" width="100%">
        <tr>
            <th class="td-id">品牌id</th>
            <th class="td-name">品牌名称</th>
            <th class="td-logo">品牌LOGO</th>
            <th class="td-desc">品牌故事</th>
            <th class="td-time">创建时间</th>
            <th class="td-update">更新时间</th>
        </tr>
    <#if list?exists>
        <#list list as item>
            <tr>
                <td class="td-id">${item["brandId"]}</td>
                <td class="td-name">${item["brandName"]}</td>
                <td class="td-logo"><image class="img-logo" src="${item["brandLogo"]}"></image></td>
                <td class="td-desc">${item["brandDesc"]}</td>
                <td class="td-time">${item["createTime"]}</td>
                <td class="td-update">${item["updateTime"]}</td>
            </tr>
        </#list>
    </#if>
    </table>
</div>

</body>

</html>