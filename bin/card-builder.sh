#!/bin/bash

resize()
{
  find . -maxdepth 1 -type f -exec convert {} -resize 800x1200\! {} \;
}

transparent()
{
  find . -maxdepth 1 -type f \( -iname "*.png" ! -iname "empty-*.png" \) -exec composite -compose copy_opacity +matte transparency.png {} {} \;
}

shrink()
{
  find . -maxdepth 1 -type f -exec convert {} -resize 50% {} \;
}

stitch()
{
  montage empty-[a-e].png -tile 5x1 -geometry +0 -background Transparent empty.png;
  montage h[2-9].png h1[0-4].png s[2-9].png s1[0-4].png d[2-9].png d1[0-4].png c[2-9].png c1[0-4].png back-[a-m].png -tile 13x5 -geometry +0 -background Transparent all.png;
}

optimize()
{
  optipng *.png;
  jpegoptim *.jpg;
}

process()
{
  echo "Processing $1...";
  rm -rf $1;
  mkdir $1;
  cd $1;
  cp ../$2/* .;

  if [ "$2" == "_unprocessed" ]
  then
    echo "Resizing to 800x1200...";
    resize;
    echo "Applying transparency...";
    transparent;
    echo "Stitching...";
    stitch;
    echo "Removing unused files..."
    rm transparency.png
    rm back-*.png
    rm empty-*.png
    rm c*.png
    rm d*.png
    rm h*.png
    rm s*.png
  else
    echo "Shrinking...";
    shrink;
  fi

  echo "Optimizing...";
  optimize;

  cd ..
}

cardset()
{
  echo "Processing card set $1"
  pushd $1
  process x-large _unprocessed
  process large x-large
  process medium large
  process small medium
  process x-small small
  popd
}

pushd ../public/images/game/cards
cardset standard
# cardset classic

