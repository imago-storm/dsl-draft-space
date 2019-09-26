


File file = new File('/opt/electriccloud/electriccommander/plugins/CB-DSL-DependencyManagement-1.0.0.0', 'dependencies.zip')

String chunk = file.bytes.encodeBase64()
// file.withInputStream {
//     it.read(buffer, 1024, 1024)
// }
return [
    chunk: chunk
]

//
// int startWithFile = 0
// List<String> chunk = []
// int size = 0
// List<String> files = fileList.split("\n")
// int i = startWithFile
// while(size < chunkSize) {
//     String fileName = files[i]
//     File f = new File(path, fileName)
//     if (f.exists()) {
//         byte[] b = f.bytes
//         size += b.size()
//         chunk.add(b.encodeBase64().toString())
//     }
//     else {
//         throw new RuntimeException("File $fileName does not exist")
//     }
// }
//
// return [
//     chunk: chunk
// ]
