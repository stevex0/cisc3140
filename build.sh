function compile() {
    javac LabDueDates.java
}

function run() {
    java LabDueDates < $1
}

function compile_and_run() {
    echo -n "File: "
    read -r file
    compile
    run $file
}