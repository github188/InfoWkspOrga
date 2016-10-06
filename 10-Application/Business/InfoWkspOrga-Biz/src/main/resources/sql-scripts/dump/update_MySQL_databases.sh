#!/bin/bash 

# itinialisation des variables
bprAffectFile="init"
vigireportFile="init"


# import BPR-Affect at first
for f in *.bz2
do
  echo "Processing $f file..."

  bprAffectFile="$f"
  echo Load bprAffectFile : $bprAffectFile

  if [[ -f $bprAffectFile ]];
  then
    echo unzip : $bprAffectFile 
    bzip2 -d $bprAffectFile 
  
    bprAffectFile="${bprAffectFile%.*}"

    mysql --user=root --password=b1-del1very -f -D bpr_affect < start_restore_database.sql 
    mysql --user=root --password=b1-del1very -f -D bpr_affect < $bprAffectFile 
    mysql --user=root --password=b1-del1very -f -D bpr_affect < finish_restore_database.sql
  
    echo Remove : $bprAffectFile  
    rm -f $bprAffectFile
  fi
done

echo " "
echo "---------------------------------------------------------------------------------------------------"
echo " "

# Finally import Vigireport

for f in *.gz
do
  echo "Processing $f file..."

  vigireportFile="$f"
  echo Load vigireportFile : $vigireportFile

  if [[ -f $vigireportFile ]]; 
  then
    echo unzip : $vigireportFile  
    gzip -d $vigireportFile

    vigireportFile="${vigireportFile%.*}"

    mysql --user=root --password=b1-del1very -f -D vigireport < start_restore_database.sql 
    mysql --user=root --password=b1-del1very -f -D vigireport < $vigireportFile
    mysql --user=root --password=b1-del1very -f -D vigireport < finish_restore_database.sql 

    echo Remove : $vigireportFile
    rm -f $vigireportFile
  fi
done
