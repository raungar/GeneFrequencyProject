#! /usr/bin/perl
#problems:  replace space with underline
#			add dashed line to end of paragraph

#This converts a FASTA file to a Phylip file

#define program usage
$usage = "Please enter a FASTA file in command line";
$ARGV[O] or die $usage;

#open FASTA file
open( FASTA, $ARGV[0]) || die "Can't open $ARGV[0]";#THE ARGUMENT IS THE FILE NAME

$firstnum;
$sequence;
$sequence2;
$count = 0;
$switch = false; #resets sequence
@name;
@seq;
$name_num = 0;
#read FASTA file contents
while ($line = <FASTA>)
{
	$secondnum++;

	#if > increment first number
	if( $line =~ /\A>/)
	{
		$firstnum++;
		if( $line =~ /\A>(.*)/){

			$name_length = 24 - length($line);
			$temp = $1;
			$line =~  s/\s/_/g;
			$line = substr($line, 1, -1);
			#print $1, "\n";
			
			for ($i = 0; $i < $name_length; $i++)
			{
				$line .= " " ;
			}
						#print $line;

			#print "TEMP::     ", $temp;
			$name[$name_num] = $line;
			$name_num++;
			$switch = true;



			#if ($line =~ / +/)
			#{
				#print "space";
			#}
		}
	}
	else
	{
		#print "\n$name_num: " , $sequence;
		#print $switch;
		if ($switch eq 'true')
		{
			$sequence = '';
		}
		if ($line =~ /\n/)
		{
			#$sequence = chomp($line);
			
			#print "\n$name_num: " , $`;
			$sequence2 = $`;
			#print " ", $sequence2;
		}
		#print $';
		#print "MYLINE: " ,$sequence, "\n";

		

		#print "\t", $sequence2;

		$sequence = $sequence2 . '' . $sequence;
		
		#print $sequence;
		$seq[$name_num-1] = $sequence;
		#print $seq
		#print $sequence;
		$switch = false;
		#print $line, "\n";
	}
	#print $sequence;
	$count++;
	
}


open(STDOUT, '>', "atp2.phy");
for( $i = 0; $i < @name; $i++)
{
	$substring = $seq[i];
	$len = length($substring);
	for( $j = $len; $j < 1542; $j++)
	{
		#$substring = substr $substring, $len - 3, 0, "_"; #add _ to third to end of substring
		substr($substring, $len - 3, 0) = "-";

		
	}
	#print $substring, " ", $len;
	$seq[$i] = $substring;

}
print $name_num, " " , length($seq[0]), "\n";
for ( $i = 0; $i < @name; $i++)
{
	print $name[$i],  $seq[$i], "\n";
}


#print $secondnum , " " , $firstnum , " " , "\n";
#close FASTA filę
close FASTA;


#print the phylip format
#print numbers 
#print sequences
exit; 