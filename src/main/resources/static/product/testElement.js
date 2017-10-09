/**
 * Created by liwenlong on 2017/9/19.
 */
var Main = {
    template: '<el-upload action="element/file/uploadFile" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove"><i class="el-icon-plus"></i></el-upload><el-dialog v-model="dialogVisible" size="tiny"><img width="100%" :src="dialogImageUrl" /></el-dialog>',
    data() {
        return {
            dialogImageUrl: '',
            dialogVisible: false
        };
    },
    methods: {
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        }
    }
}
var Ctor = Vue.extend(Main);
Vue.component('my-component', Ctor);
new Vue({
    el: "#app"
});