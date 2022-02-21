import FileSaver from "file-saver";
export default class fileSave {
  /**
   * 导出Pdf文件
   * @param {*} res   文件流
   * @param {*} name  文件名
   */
  static getPdf(res, name) {
    let blob = new Blob([res], {
      type: "application/pdf"
    });
    FileSaver.saveAs(blob, name + ".pdf");
  }

  /**
   * 导出Excel文件
   * @param {*} res   文件流
   * @param {*} name  文件名
   */
  static getExcel(res, name) {
    let blob = new Blob([res], {
      type: "application/vnd.ms-excel"
    });
    FileSaver.saveAs(blob, name + ".xlsx");
  }

  /**
   * 导出CSV文件
   * @param {*} res   文件流
   * @param {*} name  文件名
   */
  static getCsv(res, name) {
    let blob = new Blob([res], {
      type: "application/vnd.ms-excel"
    });
    FileSaver.saveAs(blob, name + ".csv");
  }

  /**
   * 导出图片1
   * @param {*} url 图片地址
   * @param {*} name  文件名
   */
  static getImgURLs(url, name) {
    let last = url.substring(url.lastIndexOf("."), url.length);
    FileSaver.saveAs(url, `${name}${last}`);
  }
  /**
   * 导出图片2
   * @param {*} res 文件流
   * @param {*} name  文件名
   */
  static downLoadImg(res, filename) {
    let blob = new Blob([res], {
      type: "image/jpeg"
    });
    FileSaver.saveAs(blob, `${filename}.jpg`);
  }
    /**
     * 导出文字 .txt文件
     * @param {*} res   文件流
     * @param {*} name  文件名
     */
      static getTxt(res, name) {
      let blob = new Blob([res], {
          type: "text/plain;charset=utf-8"
      });
      FileSaver.saveAs(blob, name + ".txt");
  }
}
 /** 使用
  第一步先安装file-saver插件: npm install file-saver --save
 1、获取下载文件名
  Vue.prototype.$getDownloadName = function (res) {
    let cod = res.headers["content-disposition"];
    let cpos = cod.indexOf("=");
    let exportName = cod.substring(cpos + 1, cod.length)
    return decodeURIComponent(exportName)
  }
  2、接口
  export function downLoad(params) {
    return request({
      url: urlPrefix + '/downLoad',
      method: 'get',
      responseType: 'blob',
      params,
      paramsSerializer: params => {
        return qs.stringify(params, { indices: false })
      },
    })
  }
  3、接口调用
  import exportFile from '@/utils/exportFile'

    async onDownLoad() {
        const [err, res] = await to(API.downLoad(params))
        exportFile.getPdf(res.data, this.$getDownloadName(res))
}
 */




