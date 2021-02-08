package com.jeson316.pic

import javax.imageio.ImageIO
import java.io.FileOutputStream
import java.io.OutputStream
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.Image
import java.io.IOException
import java.io.File


class PicUtils {

    private var destFile: String? = null
    private var width = 0
    private var height = 0
    private var img: BufferedImage? = null
    private var ext: String? = null

    //缩放图片工具的构造函数
    @kotlin.Throws(IOException::class)
    constructor(srcFile: String) {
        //得到最后一个.的位置
        val index: Int = srcFile.lastIndexOf(".")
        //获取被缩放的图片的格式
        ext = srcFile.substring(index + 1)
        //获取目标路径(和原始图片路径相同,在文件名后添加了一个_s)
        destFile = srcFile.substring(0, index) + "_s." + ext
        //读取图片,返回一个BufferedImage对象
        img = ImageIO.read(File(srcFile))
        //获取图片的长和宽
        width = img!!.width
        height = img!!.height
    }


    //缩放图片工具的构造函数
    @kotlin.Throws(IOException::class)
    constructor(srcFile: String, fixName:String) {
        //得到最后一个.的位置
        val index: Int = srcFile.lastIndexOf(".")
        //获取被缩放的图片的格式
        ext = srcFile.substring(index + 1)
        //获取目标路径(和原始图片路径相同,在文件名后添加了一个_s)
        destFile = srcFile.substring(0, index) + fixName+"_." + ext
        //读取图片,返回一个BufferedImage对象
        img = ImageIO.read(File(srcFile))
        //获取图片的长和宽
        width = img!!.width
        height = img!!.height
    }


    /**
     * 按比例对图片进行缩放.
     * @param scale 缩放比例
     * @throws IOException
     */
    @kotlin.Throws(IOException::class)
    fun zoomByScale(scale: Double) {
        //获取缩放后的长和宽
        val _width = (scale * width).toInt()
        val _height = (scale * height).toInt()
        //获取缩放后的Image对象
        val _img = img!!.getScaledInstance(_width, _height, Image.SCALE_DEFAULT)
        //新建一个和Image对象相同大小的画布
        val image = BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB)
        //获取画笔
        val graphics = image.createGraphics()
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(_img, 0, 0, null)
        //释放资源
        graphics.dispose()
        //使用ImageIO的方法进行输出,记得关闭资源
        val out: OutputStream = FileOutputStream(destFile)
        ImageIO.write(image, ext, out)
        out.close()
    }


    /**
     * 指定长和宽对图片进行缩放
     * @param width 长
     * @param height 宽
     * @throws IOException
     */
    @kotlin.Throws(IOException::class)
    fun zoomBySize(width: Int, height: Int) {
        //与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
        val _img = img!!.getScaledInstance(width, height, Image.SCALE_DEFAULT)
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        graphics.drawImage(_img, 0, 0, null)
        graphics.dispose()
        val out: OutputStream = FileOutputStream(destFile)
        ImageIO.write(image, ext, out)
        out.close()
    }
}