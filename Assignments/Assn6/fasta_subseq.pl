#! /usr/bin/perl


#print "Please enter the name of a FASTA file to extract data from: ";
#$file_name = "nad_genes.fasta"; ####FIX me!!!
#$file_name = <STDIN>;
$usage = "Please enter a FASTA file in the command line\n";
$ARGV[O] or die $usage;

#open FASTA file
open( FASTA, $ARGV[0]) || die "Can't open $ARGV[0]";#THE ARGUMENT IS THE FILE NAME

$file_name = $ARGV[0];


print "What start position do you want to include?\t";
use Scalar::Util qw(looks_like_number);
my $input = <STDIN>;
chomp($input);
if (looks_like_number($input))
{
	$start = $input;
}
else
{
	while (!looks_like_number($input))
	{
		print "Please enter a valid start number: ";
		my $input = <STDIN>;
		chomp($input);
		print $input;
		if (looks_like_number($input))
		{
			last;
		}
	}

	$start = input;
}

print "\nWhat end position do you want to include?\t";
use Scalar::Util qw(looks_like_number);
my $input = <STDIN>;
chomp($input);
while (!looks_like_number($input))
{
	print "Please enter a valid end number: ";
	my $input = <STDIN>;
	chomp($input);
	if (looks_like_number($input))
	{
		last;
	}
}

$end = $input;

while ($end <= $start)
{
	print "Please enter an end number larger than your start value: ";
	my $input = <STDIN>;
	chomp($input);
	$end = $input;
	if ($end gt $start)
	{
		last;
	}
}

while ($line = <FASTA>){

		if( $line =~ /\A>(.*)/){
			#DO Nothing
		}
		else
		{
			$subseq = substr $line, $start-1, ($end - $start) + 1; 
		}

}

#$subseq = substr $sequence, $start-1, ($end - $start)-1; 


print "\nDo you want the reverse complement? If yes, press y. If no, press any other key: ";
my $input = <STDIN>;
chomp($input);

if ($input eq "y" || $input eq "Y")
{
	$reverse = reverse $subseq;
	$reverse =~ tr/ACGT/TGCA/;
	$strand = "reverse";

	print ">" , $file_name, "_" , $start , "_" , $end, "_" , $strand , " " , $reverse ,"\n";

}

else
{
	$strand = "forward";
	print ">" , $file_name, "_" , $start , "_" , $end, "_" , $strand , " " , $subseq ,"\n";

}
