Vue.componet('my-header',{
    data:function (){
        return{

        }
    },
    methods:{

    },
    created:function () {
    },
    template:`
        <el-header height="80px">
            <div class="center">
                <el-row gutter="20">
                    <el-col span="6">
                        <img src="imgs/icon.png" width="196" height="65">
                    </el-col>
                    <el-col span="10">
                        <el-menu mode="horizontal" active-text-color="orange">
                            <el-menu-item index="0">首页</el-menu-item>
                            <el-menu-item index="1">食谱</el-menu-item>
                            <el-menu-item index="2">视频</el-menu-item>
                            <el-menu-item index="3">资讯</el-menu-item>
                        </el-menu>
                    </el-col>
                    <el-col span="6">
                        <el-input style="margin-top: 15px" placeholder="请输入搜索的关键字">
                            <el-button slot="append" icon="el-icon-search"></el-button>
                        </el-input>
                    </el-col>
                    <el-col span="2">
                        <el-popover
                                placement="top-start"
                                title="欢迎来到烘焙坊!"
                                width="200"
                                trigger="hover">
                            <div slot="reference">
                                <i class="el-icon-user"
                                   style="font-size: 30px;position: relative;top: 20px"></i>
                            </div>
                            <el-button type="info" @click="location.href='/reg.html'">注册</el-button>
                            <el-button style="background-color: orange" @click="location.href='/login.html'">登录</el-button>
                        </el-popover>
                    </el-col>
                </el-row>
            </div>
        </el-header>
    `
})