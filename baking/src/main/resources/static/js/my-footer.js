Vue.component('my-footer', {
    data: function () {
        return {
        }
    },
    methods: {
    },
    created: function () {

    },
    template: `
            <div class="center" style="text-align: center;color: #666">
                <el-row>
                    <el-col span="8">
                        <img src="imgs/icon.png">
                        <p>教程灵感就看烘焙坊</p>
                        <p>烘焙行业网络社区平台</p>
                        <p>全国百城上千个职位等你来</p>
                    </el-col>
                    <el-col span="8">
                        <el-row id="footer_center">
                            <el-col span="8">
                                <h3>关于我们</h3>
                                <p>关于我们</p><p>烘焙学院</p>
                                <p>烘焙食谱</p><p>分类信息</p>
                                <p>求职招聘</p><p>社区交流</p>
                            </el-col>
                            <el-col span="8">
                                <h3>支持与服务</h3>
                                <p>联系我们</p><p>广告投放</p>
                                <p>用户协议</p><p>友情链接</p>
                                <p>在线反馈</p><p>我发投稿</p>
                            </el-col>
                            <el-col span="8">
                                <h3>底部导航</h3>
                                <p>Archiver</p><p>手机版</p>
                                <p>小黑屋</p>
                            </el-col>
                        </el-row>

                    </el-col>
                    <el-col span="8">
                        <div style="font-size: 58px;margin-top: 30px">
                            <span style="color: orange">烘焙</span><span style="color: #666666">坊</span>
                        </div>
                        <p>烘焙行业网络社区平台</p>
                    </el-col>
                </el-row>
            </div>
    `
})