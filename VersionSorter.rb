class VersionSorter
	VERSION = /((([a-z]|([0-9][1-9]+)|[0-9])\.?)+)(?<!\.)$/

	def sortVersions(first, second)
		if !(first =~ VERSION && second =~ VERSION)
			return nil	
		end

		firstItems = first.split(/\./)
		secondItems = second.split(/\./)

		lengths = [firstItems.length, secondItems.length]
		maxLength = lengths.max

		for index in 0..maxLength-1
		  
			lastOfFirst = ((index+1) == firstItems.length)
			lastOfSecond = ((index+1) == secondItems.length)
			
			if(!(lastOfFirst || lastOfSecond))
				#Neither string is ending. Look for a difference, if none, go deeper.
				result = firstItems[index] <=> secondItems[index]
				if(result == 0)
					next
				elsif(result > 0)
					return "FIRST"
				elsif(result < 0)
					return "SECOND"
				end
      elsif(lastOfFirst && lastOfSecond)
				#Both strings are ending. Look for a difference, if none, EQUAL.
			  result = firstItems[index] <=> secondItems[index]
				if(result == 0)
					return "EQUAL"
				elsif(result > 0)
					return "FIRST"
				elsif(result < 0)
					return "SECOND"
				end
			elsif(lastOfFirst)
				#Version1 is ending, but Version 2 is not.
				#Unless 1's item is larger than 2' item, we can assume version 2 is newer
				result = firstItems[index] <=> secondItems[index]
				if(result <= 0)
					return "SECOND"
				elsif(result > 0)
					return "FIRST"
				end
			elsif(lastOfSecond)
				#Version2 is ending, but Version 1 is not.
				#Unless 2's item is larger than 1' item, we can assume version 1 is newer
				result = secondItems[index] <=> firstItems[index]
				if(result <= 0)
					return "FIRST"
				elsif(result > 0)
					return "SECOND"
				end
			end
		end
	end
end

version1 = "1.3.2"
version2 = "1.3"

sorter = VersionSorter.new
newer = sorter.sortVersions(version1, version2)
puts "The " + newer + " version is more recent than the other"