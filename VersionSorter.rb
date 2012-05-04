class VersionSorter
	VERSION = /((([a-z]|([0-9][1-9]+)|[0-9])\.?)+)(?<!\.)$/

	def sortVersions(first, second)
		if !(first =~ VERSION && second =~ VERSION)
			return nil	
		end

		firstItems = first.split(/\./)
		secondItems = second.split(/\./)

		lengths = [firstItems.length, secondItems.length]
		minLength = lengths.min

		for index in 0..minLength-1
			lastOfFirst = ((index+1) == firstItems.length)
			lastOfSecond = ((index+1) == secondItems.length)
			result = firstItems[index] <=> secondItems[index]
			
			if(result == 0)
				if(lastOfFirst && lastOfSecond)
				  return "EQUAL"
				elsif(lastOfFirst)
				  return "SECOND"
				elsif(lastOfSecond)
				  return "FIRST"
				else
					next
				end
      elsif(result > 0)
				return "FIRST"
			elsif(result < 0)
				return "SECOND"
			end
		end
		
		return "FAILED"
		
	end
end

version1 = "1.a"
version2 = "1.d"

sorter = VersionSorter.new
newer = sorter.sortVersions(version1, version2)
puts "The " + newer + " version is more recent than the other"