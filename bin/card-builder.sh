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

rasterize()
{
  cd $1
  echo "Rasterizing Card Backs"
  svgexport _vector/back-a.svg _unprocessed/back-a.png 4000:6000
  svgexport _vector/back-b.svg _unprocessed/back-b.png 4000:6000
  svgexport _vector/back-c.svg _unprocessed/back-c.png 4000:6000
  svgexport _vector/back-d.svg _unprocessed/back-d.png 4000:6000
  svgexport _vector/back-e.svg _unprocessed/back-e.png 4000:6000
  svgexport _vector/back-f.svg _unprocessed/back-f.png 4000:6000
  svgexport _vector/back-g.svg _unprocessed/back-g.png 4000:6000

  echo "Rasterizing Hearts"
  svgexport _vector/h2.svg _unprocessed/h2.png 4000:6000
  svgexport _vector/h3.svg _unprocessed/h3.png 4000:6000
  svgexport _vector/h4.svg _unprocessed/h4.png 4000:6000
  svgexport _vector/h5.svg _unprocessed/h5.png 4000:6000
  svgexport _vector/h6.svg _unprocessed/h6.png 4000:6000
  svgexport _vector/h7.svg _unprocessed/h7.png 4000:6000
  svgexport _vector/h8.svg _unprocessed/h8.png 4000:6000
  svgexport _vector/h9.svg _unprocessed/h9.png 4000:6000
  svgexport _vector/h10.svg _unprocessed/h10.png 4000:6000
  svgexport _vector/h11.svg _unprocessed/h11.png 4000:6000
  svgexport _vector/h12.svg _unprocessed/h12.png 4000:6000
  svgexport _vector/h13.svg _unprocessed/h13.png 4000:6000
  svgexport _vector/h14.svg _unprocessed/h14.png 4000:6000

  echo "Rasterizing Spades"
  svgexport _vector/s2.svg _unprocessed/s2.png 4000:6000
  svgexport _vector/s3.svg _unprocessed/s3.png 4000:6000
  svgexport _vector/s4.svg _unprocessed/s4.png 4000:6000
  svgexport _vector/s5.svg _unprocessed/s5.png 4000:6000
  svgexport _vector/s6.svg _unprocessed/s6.png 4000:6000
  svgexport _vector/s7.svg _unprocessed/s7.png 4000:6000
  svgexport _vector/s8.svg _unprocessed/s8.png 4000:6000
  svgexport _vector/s9.svg _unprocessed/s9.png 4000:6000
  svgexport _vector/s10.svg _unprocessed/s10.png 4000:6000
  svgexport _vector/s11.svg _unprocessed/s11.png 4000:6000
  svgexport _vector/s12.svg _unprocessed/s12.png 4000:6000
  svgexport _vector/s13.svg _unprocessed/s13.png 4000:6000
  svgexport _vector/s14.svg _unprocessed/s14.png 4000:6000

  echo "Rasterizing Diamonds"
  svgexport _vector/d2.svg _unprocessed/d2.png 4000:6000
  svgexport _vector/d3.svg _unprocessed/d3.png 4000:6000
  svgexport _vector/d4.svg _unprocessed/d4.png 4000:6000
  svgexport _vector/d5.svg _unprocessed/d5.png 4000:6000
  svgexport _vector/d6.svg _unprocessed/d6.png 4000:6000
  svgexport _vector/d7.svg _unprocessed/d7.png 4000:6000
  svgexport _vector/d8.svg _unprocessed/d8.png 4000:6000
  svgexport _vector/d9.svg _unprocessed/d9.png 4000:6000
  svgexport _vector/d10.svg _unprocessed/d10.png 4000:6000
  svgexport _vector/d11.svg _unprocessed/d11.png 4000:6000
  svgexport _vector/d12.svg _unprocessed/d12.png 4000:6000
  svgexport _vector/d13.svg _unprocessed/d13.png 4000:6000
  svgexport _vector/d14.svg _unprocessed/d14.png 4000:6000

  echo "Rasterizing Clubs"
  svgexport _vector/c2.svg _unprocessed/c2.png 4000:6000
  svgexport _vector/c3.svg _unprocessed/c3.png 4000:6000
  svgexport _vector/c4.svg _unprocessed/c4.png 4000:6000
  svgexport _vector/c5.svg _unprocessed/c5.png 4000:6000
  svgexport _vector/c6.svg _unprocessed/c6.png 4000:6000
  svgexport _vector/c7.svg _unprocessed/c7.png 4000:6000
  svgexport _vector/c8.svg _unprocessed/c8.png 4000:6000
  svgexport _vector/c9.svg _unprocessed/c9.png 4000:6000
  svgexport _vector/c10.svg _unprocessed/c10.png 4000:6000
  svgexport _vector/c11.svg _unprocessed/c11.png 4000:6000
  svgexport _vector/c12.svg _unprocessed/c12.png 4000:6000
  svgexport _vector/c13.svg _unprocessed/c13.png 4000:6000
  svgexport _vector/c14.svg _unprocessed/c14.png 4000:6000

  cd _unprocessed
  echo "Trimming images"
  find . -maxdepth 1 -type f -exec convert -trim {} {} \;

  cp ../_vector/empty* .
  cd ../..
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
# cardset standard
# rasterize classic
cardset classic

