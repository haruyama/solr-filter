# Filters of lucene-gosen for mining

## Preparation

% cd lucene-gosen-setup
% wget https://lucene-gosen.googlecode.com/files/lucene-gosen-4.6.0-ipadic.jar
% java -jar /usr/share/java/ivy.jar -publish local -publishpattern "lucene-gosen-[revision]-ipadic.[ext]" -revision 4.6.0 -status release -overwrite -ivy ivy.xml

## Packaging jar

% sbt assembly
