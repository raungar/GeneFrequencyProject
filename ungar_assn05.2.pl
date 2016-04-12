#! /usr/bin/perl

$start = 5;
$end = 13;
$sequence = "ACTGGATCGATGCCATTAACGGTACCCGATTTAGCA";
$strand = "forward";

$subseq = substr $sequence, $start, ($end - $start); 

print ">" . $start . "_" . $end, "_" . $strand . " " . $subseq ."\n";	#prints normal;

$reverse = reverse $subseq;
$reverse =~ tr/ACGT/TGCA/;
$strand = "reverse";

print ">" . $start . "_" . $end, "_" . $strand . " " . $reverse ."\n"; #prints reverse

