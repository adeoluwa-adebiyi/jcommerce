import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

class ImageBase64Util{
    companion object {

        fun convertToBase64(imageBytes:ByteArray):String{
            val bytes = Base64.getEncoder().encodeToString(imageBytes)
            println(bytes)
            return bytes
        }

        fun getFileExtension(file: MultipartFile):String{
            return file.originalFilename.toString().split(".")[1]
        }

        fun obtainBase64Uri(imageBytes: ByteArray,file:MultipartFile):String{
            return "data:image/${getFileExtension(file)};base64,${convertToBase64(imageBytes)}"
        }
    }

}