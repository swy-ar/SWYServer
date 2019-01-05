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
            width: 60px;
            max-width: 60px;
            text-align: center;
        }

        .td-user {
            width: 120px;
            max-width: 120px;
            text-align: center;
        }

        .td-name {
            width: 300px;
            max-width: 300px;
        }

        .td-time {
            width: 150px;
            max-width: 150px;
            text-align: center;
        }

        .td-update {
            width: 150px;
            max-width: 150px;
            text-align: center;
        }

        .td-oper {
            text-align: center;
        }
    </style>

    <!-- jquery -->
    <script type="text/javascript" src="../assets/jquery-2.2.4.js"></script>

    <script type="text/javascript">
        function changeStatus(status, id) {
            // 发起ajax请求
            var url = "../api/changeStatus?id=" + id + "&status=" + status;
            var htmlobj = $.ajax({url:url, async:true});
            var respJson = JSON.parse(htmlobj.responseText);
            if (respJson.code == 200) {
                //changeStatus
            } else {
                alert(htmlobj.responseText);
            }
        }
    </script>

</head>

<body>

<h1>分享案例管理</h1>

<div>
    <table id="msgTable" name="msgTable" width="100%">
        <tr>
            <th class="td-id">序号</th>
            <th class="td-user">分享用户</th>
            <th class="td-name">视频链接</th>
            <th class="td-time">创建时间</th>
            <th class="td-update">更新时间</th>
            <th class="td-oper">操作</th>
        </tr>
    <#if list?exists>
        <#list list as item>
            <tr>
                <td class="td-id">${item["id"]}</td>
                <td class="td-user">${item["userId"]}</td>
                <td class="td-name">
                    <a href="${item["exampleUrl"]}">${item["exampleUrl"]}</a>
                    <!-- 优酷视频超链接 -->
                    <embed src="${item["exampleUrl"]}" width="480" height="400" type="application/x-shockwave-flash"></embed>
                </td>
                <td class="td-time">${item["createTime"]}</td>
                <td class="td-update">${item["updateTime"]}</td>
                <td class="td-oper">
                    <select name="status" onchange="changeStatus(this.value, ${item["id"]});">
                        <option value="0" <#if item["auditStatus"]=="0">selected</#if> >等待审核</option>
                        <option value="1" <#if item["auditStatus"]=="1">selected</#if> >审核通过</option>
                        <option value="-1" <#if item["auditStatus"]=="-1">selected</#if> >审核不过</option>
                    </select>
                </td>
            </tr>
        </#list>
    </#if>
    </table>
</div>

</body>

</html>