# Filters of lucene-gosen for mining

## Preparation

% cd lucene-gosen-setup
% cp .../lucene-gosen-4.3.1-ipadic.jar .
% java -jar /usr/share/java/ivy.jar -publish local -publishpattern "lucene-gosen-[revision]-ipadic.[ext]" -revision 4.3.1 -status release -overwrite -ivy ivy.xml

## Packaging jar

% sbt assembly
