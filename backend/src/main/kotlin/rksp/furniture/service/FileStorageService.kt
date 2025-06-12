package rksp.furniture.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Service
class FileStorageService(
    @Value("\${app.upload.dir}") private val uploadDir: String
) {
    private val root: Path = Paths.get(uploadDir).apply { Files.createDirectories(this) }

    fun store(file: MultipartFile): String {
        val ext = file.originalFilename?.substringAfterLast('.', "") ?: ""
        val filename = "${UUID.randomUUID()}${if (ext.isNotEmpty()) ".$ext" else ""}"
        val target = root.resolve(filename)
        file.inputStream.use { Files.copy(it, target) }
        return filename
    }
}
