import java.util.zip.ZipEntry
import java.util.zip.ZipException
import java.util.zip.ZipOutputStream

ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream("/tmp/dependencies.zip"))
File folder = new File('/opt/electriccloud/electriccommander/plugins/CB-DSL-DependencyManagement-1.0.0.0/agent')
folder.eachFileRecurse { File file ->
    if (!file.isDirectory()) {
        def relative = folder.toPath().relativize(file.toPath())
        println relative
        try {
            zipFile.putNextEntry(new ZipEntry(relative.toString()))
            def buffer = new byte[file.size()]
            file.withInputStream {
                zipFile.write(buffer, 0, it.read(buffer))
            }
            // println "Packed $file"
            zipFile.closeEntry()
        } catch (ZipException e) {
            throw e
        }
    }
}
zipFile.close()
println "Successfully packed $zipFile"



// folder.eachFileRecurse(FileType.DIRECTORIES) { dir ->
//     if (dir.name in ['lib', 'scripts', 'templates', 'layout', 'plugin-specs', 'doc']) {
//         dir.eachFileRecurse(FileType.FILES) { file ->
//             //check if file
//             def relative = folder.toURI().relativize(file.toURI())
//             println relative
//             try {
//                 zipFile.putNextEntry(new ZipEntry(relative.toString()))
//                 def buffer = new byte[file.size()]
//                 file.withInputStream {
//                     zipFile.write(buffer, 0, it.read(buffer))
//                 }
//                 zipFile.closeEntry()
//             } catch (ZipException e) {
//                 if (!(e.message =~ /duplicate entry/)) {
//                     throw e
//                 }
//             }
//         }
//     }
// }
// zipFile.close()