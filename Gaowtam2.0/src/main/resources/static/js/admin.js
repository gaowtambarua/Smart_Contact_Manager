console.log("admin user");

document.querySelector("#image_file_input").addEventListener('change', function(event) {
    let file = event.target.files[0]; // Corrected: using 'files' instead of 'file'
    if (file) { // Check if a file was selected
        let reader = new FileReader();

        reader.onload = function() {
            document.querySelector("#upload_image_preview").setAttribute("src", reader.result);
        };

        reader.readAsDataURL(file);
    } else {
        console.error("No file selected");
    }
});
