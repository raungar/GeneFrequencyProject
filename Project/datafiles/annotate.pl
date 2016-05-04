#! /usr/bin/perl
#This cuts annotation excel file into a readable file called annotation.txt

$usage = "Please enter a txt file in command line";
$ARGV[O] or die $usage;

open(TXT, $ARGV[0]) || die "Can't open $ARGV[0]";#THE ARGUMENT IS THE FILE NAME


while($line = <TXT>)
{
	$line =~ s/\s/\n/g; #separate the text file separated by \s(which is one line) into multiple lines
	 @data = split('\n', $line); #store in an array data each line that is split
}

%annotation_table_s; #start hash
%annotation_table_e; #end hash
%annotation_table; #general table to see what is happening

$data_size = @data; #now read through each member (or line) of previously defined array
for ($i = 0; $i < $data_size; $i++)
{
	$string = @data[$i];
	$len = length $string;
	if ($string =~ /FBtr/)
	{
		$gene_id = substr $string, 4, $len; #cuts gene id to just its numbers
	}
	else #is not a gene id, but rather contains start and end information
	{
		if ($string =~ /loc=scaffold_6540+/){	#gets rid of miscellaneous nonnumerical information

			$cut = index($string, "6540") + 5;
			$gene_info = substr $string, $cut, $len;
			$gene_info = substr $gene_info, 0, -1; #cuts upto 6540 and removes semicolon
			$gene_info_len = length $gene_info;

			if ($gene_info =~ /join/) #cuts join information
			{
				$gene_info	= substr $gene_info, 5, $gene_info_len;
				$gene_info = substr $gene_info, 0, -2;
			}
			if ($gene_info =~ /complement/) #cuts join information
			{
				$gene_info	= substr $gene_info, 11, $gene_info_len;
				$gene_info = substr $gene_info, 0, -2;
			}

			while ($gene_info =~ /\.+/) #gets first and last number
			{
				$gene_info =~ s/\.\./\t/;
				$current_len = length $gene_info;
				$tab_loc_start = index($gene_info, "\t");
				$tab_loc_end = rindex($gene_info, "\t");
				$start = substr $gene_info, 0, $tab_loc_start;
				$end = substr $gene_info, $tab_loc_end +1, $current_len;
			}

			$annotation_table{$gene_id} = $gene_info;
			$annotation_table_s{$gene_id} = $start; #stores start in start hash
			$annotation_table_e{$gene_id} = $end; #stores end in end hash
		}
	}

}

open(STDOUT, '>', "new_annotation.txt"); #print output to new_annotation.txt

#print 
foreach (sort keys %annotation_table) 
{
    print "$_", "\t", $annotation_table_s{$_},"\t" ,$annotation_table_e{$_}, "\n";
}



close TXT;

exit;