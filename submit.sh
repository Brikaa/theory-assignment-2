submission_name=20206011_20206043_20206123_S5
submission_dir=submission/$submission_name
rm -rf submission && \
mkdir -p $submission_dir && \
pandoc README.md -o $submission_dir/README.pdf && \
cp Main.java $submission_dir/. && \
cd submission && \
zip -r $submission_name.zip $submission_name
